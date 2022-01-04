const createError = require('http-errors')
const express = require('express')
const session = require('express-session')
const path = require('path')
const cookieParser = require('cookie-parser')
const bodyParser = require('body-parser')
const logger = require('morgan')

const mongoose = require('./config/mongoose')
// const localHostPort = 4000

// ROUTES
const indexRouter = require('./routes/index')
const crudRouter = require('./routes/crud')
const loginRouter = require('./routes/login')

const app = express()

app.set('views', path.join(__dirname, 'views'))
app.set('view engine', 'jade')

app.use(logger('dev'))
app.use(express.json())
app.use(express.urlencoded({ extended: true }))
app.use(bodyParser.urlencoded({ extended: true }))
app.use(cookieParser())
app.use(express.static(path.join(__dirname, 'public')))

app.use(session({
  resave: false,
  saveUninitialized: false,
  secret: 'Eren AOT'
}))

app.use('/', indexRouter)
app.use('/crud', crudRouter)
app.use('/auth', loginRouter)

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404))
})

// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message
  res.locals.error = req.app.get('env') === 'development' ? err : {}

  // render the error page
  res.status(err.status || 500)
  res.render('error')
})

mongoose.connect().catch(err => {
  console.log('ERROR M : ' + err)
})

module.exports = app
