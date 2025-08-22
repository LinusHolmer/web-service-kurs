import express from "express"
import "dotenv/config"

const app = express()
const port: number = 3000
const secret = process.env.SECRET // not work

app.get("/", (req, res) => {
    res.status(200).send(
        "<h2>My website</h2> <p>Hello world!</p>"
        +"<ul><li>rad1</li></ul>"
    )
    
       /*
       res.send(new Buffer('test'));
       */
       
      
})

//Start server on Port variable
app.listen(port, () => {
    console.log(`Listening on port ${port}`)
    console.log(secret)
})