//jshint esversion:6
require('dotenv').config(); //this has to be at the top to save our secrets
const express = require("express");
const bodyParser = require ("body-parser");
const ejs = require("ejs");
const mongoose = require ("mongoose");
const encrypt = require ("mongoose-encryption");


const app=express();

console.log(process.env.API_KEY);

app.use(express.static("public"));
app.set('view engine', 'ejs');
app.use(bodyParser.urlencoded({
    extended: true
}));

mongoose.connect("mongodb://localhost:27017/userDB", {useNewUrlParser: true });

const userSchema = new mongoose.Schema({
    email: String,
    password: String
});


userSchema.plugin(encrypt, {secret:process.env.secret, encryptedFields: ['password'] });

const User =new mongoose.model("User", userSchema);

app.get("/", function(req,res){
    res.render("home");
});

app.get("/login", function(req,res){
    res.render("login");
});

app.get("/register", function(req,res){
    res.render("register");
});

app.post("/register", function(req, res){
    const newUser =new User({
        email: req.body.username,
        password: req.body.password
    });
    
    newUser.save()
    .then(() => {
        res.render("secrets");
    })
    .catch(err => {
        console.log(err);
    });

});

app.post("/login", function(req,res){
    const username =req.body.username;
    const password= req.body.password;

    User.findOne({email: username})
        .then(foundUser => {
            if (foundUser && foundUser.password === password) {
                res.render("secrets");
            }
        })
        .catch(err => {
            console.log(err);
        });        
    });

app.listen(3000, function(){
    console.log("server started on port 3000.");
});