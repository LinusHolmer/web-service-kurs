import express from "express"
import "dotenv/config"

const app = express()
const port: number = Number(process.env.PORT) || 3000
const secret = process.env.SECRET // not work

app.get("/", (req, res) => {
    res.status(200).json("Hello world")
    
    
       /*
       res.send(new Buffer('test'));
       */
       
      
})

//Start server on Port variable
app.listen(port, "0.0.0.0", () => {
    console.log(`Listening on port ${port}`)
    // console.log(secret)
})