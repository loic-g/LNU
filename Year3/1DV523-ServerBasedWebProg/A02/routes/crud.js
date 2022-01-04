const express = require('express')
const router = express.Router()
const controller = require('../controllers/crudController.js')

module.exports = router

// Call the methods here with router.get and router.post + link it to the crudController methods.

router.get('/add', controller.addSnippetForm)
router.post('/add', controller.addSnippetPost)

router.get('/update', controller.editSnippetForm)
router.post('/update', controller.editSnippetProcess)

router.get('/delete', controller.deleteSnippetForm)
router.post('/delete', controller.deleteSnippetProcess)
