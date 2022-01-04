const express = require('express')
const lrouter = express.Router()
const lController = require('../controllers/loginController.js')

module.exports = lrouter

lrouter.get('/login', lController.loginForm)
lrouter.post('/login', lController.loginProcess)

lrouter.get('/register', lController.registerForm)
lrouter.post('/register', lController.registerProcess)

lrouter.get('/logout', lController.logoutForm)
lrouter.post('/logout', lController.logoutProcess)
