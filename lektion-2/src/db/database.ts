import { Db, MongoClient, ServerApiVersion } from "mongodb"
import { validateSecret } from "../security/validateEnvt.js"

let client: MongoClient
let isConnected = false
let dbName: string

export async function runDB(): Promise<void> {
    if (isConnected) return

    // Load env vars here, after dotenv has run
    const uri = validateSecret(process.env.DB_CONNECTION_STRING)
    dbName = validateSecret(process.env.DB_NAME)

    client = new MongoClient(uri, {
        serverApi: {
            version: ServerApiVersion.v1,
            strict: true,
            deprecationErrors: true,
        },
    })

    try {
        await client.connect()
        await client.db("admin").command({ ping: 1 })
        isConnected = true
        console.log("Db is up and running")
    } catch (err) {
        console.error(err)
        throw err
    }
}

export function getDB(): Db {
    if (!isConnected) {
        throw new Error("Tried to access DB before connecting")
    }
    return client.db(dbName)
}

export async function closeDB(): Promise<void> {
    if (!client) return
    await client.close()
    isConnected = false
    console.log("Mongodb connection closed")
}
