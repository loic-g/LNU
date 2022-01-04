'use strict'
const mongoose = require('mongoose')
const def = require('./default.json')

mongoose.set('useCreateIndex', true)
mongoose.set('useFindAndModify', false)

module.exports.connect = async () => {
  mongoose.connection.on('connected', () => console.log('Mongoose connection open'))
  mongoose.connection.on('disconnect', () => console.log('Mongoose disconnected'))
  mongoose.connection.on('error', (err) => console.log('Mongoose error: ' + err))

  process.on('SIGINT', () => {
    mongoose.connection.close(() => {
      console.log('Mongoose Disconnected due to program stop')
      process.exit(0)
    })
  })

  return mongoose.connect(def.mongoURI, { useNewUrlParser: true, useUnifiedTopology: true })
}
