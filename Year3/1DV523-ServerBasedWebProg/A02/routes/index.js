const express = require('express')
const router = express.Router()
const crudController = require('../controllers/crudController')

/* GET home page. */
router.get('/', crudController.list)

module.exports = router
