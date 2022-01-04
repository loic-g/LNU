var express = require('express');
var router = express.Router();
const controller = require('../controller/controller')
/* GET home page. */
router.get('/',controller.fetchIssues );
router.post('/',controller.webHook)

module.exports = router;
