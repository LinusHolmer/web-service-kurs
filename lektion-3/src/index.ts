import express from "express";
import "dotenv/config";
import { closeDB, getDB, runDB } from "./db/database.js";
import { Db, MongoClient, ServerApiVersion } from "mongodb";

const app = express();
const port: number = Number(process.env.PORT) || 3000;
const address: string = "0.0.0.0";

async function startServer() {
  try {
    await runDB();
    app.listen(port, () => {
      console.log(`Listening to port ${port}`);
      console.log(`Start the app: http://localhost:${port}`);
    });
    process.on("SIGINT", async () => {
      console.log("Cleaning up...");
      await closeDB();
      process.exit(0);
    });
  } catch (error) {
    console.log(error);
  }
}
startServer();

app.get("/api/v1/database/comments/:username", async (req, res) => {
  const db: Db = getDB();
  const result = await db
    .collection("comments")
    .find({ name: req.params.username }) //Mercedes Tyler
    .limit(25)
    .toArray();

  if (result.length === 0) {
    res.status(404).send({ message: "Nothing was found" });
    return;
  }

  res.send(result);
});

/*
app.get("/", (req, res) => {
  res.send({ message: "Hello world!" });


       res.send(new Buffer('test'));
       
});
*/

/*Start server on Port variable
app.listen(port, address, () => {
    console.log(`Listening on port ${port}`)
    // console.log(secret)
})
    */
