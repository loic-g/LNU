const mongoose = require('mongoose')

const snippetSchematic = new mongoose.Schema({
  authorName: { type: String, required: true },
  authorId: { type: String, required: true },
  snippet: { type: String }

})

module.exports = mongoose.model('Snippet', snippetSchematic)
