const Snippet = require('../models/snippet')
const User = require('../models/user')

const crudController = {}
module.exports = crudController

crudController.list = async (req, res) => {
  console.log('[R] Read from DB')
  const snippets = await Snippet.find()
  res.render('welcome', {
    flash: req.session.flash,
    snippets: snippets,
    user: req.session.user
  })
}

crudController.addSnippetForm = (req, res) => {
  console.log('[C] Add snippet')
  const flash = req.session.flash
  req.session.flash = null

  const user = req.session.user

  if (user) {
    res.render('snippetForm', {
      flash: flash,
      user: req.session.user
    })
  } else {
    req.session.flash = 'You need to be connected to be able to add snippets'
    res.redirect('./../auth/login')
  }
}

crudController.addSnippetPost = async (req, res) => {
  const textarea = req.body.textarea
  const user = req.session.user
  const currentUser = await User.findOne({ username: user }).catch(err => { console.error(err) })

  if (currentUser) {
    const userID = currentUser._id
    const username = currentUser.username
    await Snippet.create({
      authorName: username,
      authorId: userID,
      snippet: textarea
    })
    req.session.flash = 'The snippet was added successfully!'
    res.redirect('./../')
  } else {
    req.session.flash = 'You need to be connected to be able to add snippets'
    res.redirect('./../auth/login')
  }
}

crudController.editSnippetForm = async (req, res) => {
  console.log('[U] Update snippet')
  const flash = req.session.flash
  req.session.flash = null
  const user = req.session.user

  if (user) {
    const snippets = await Snippet.find({ authorName: user }).catch(err => { console.error(err) })
    res.render('snippetEditForm', {
      flash: flash,
      snippets: snippets,
      user: user
    })
  } else {
    req.session.flash = 'You need to be connected to be able to edit/update snippets'
    res.redirect('./../auth/login')
  }
}

crudController.editSnippetProcess = async (req, res) => {
  const snippetId = req.body.updatingSnippet
  const test = req.body
  let updatedText
  for (const key in test) {
    if (key === snippetId) {
      updatedText = test[key]
      break
    }
  }

  const snippetToUpdate = await Snippet.findById(snippetId)
  if (snippetToUpdate) {
    snippetToUpdate.snippet = updatedText
    await snippetToUpdate.save()
    req.session.flash = 'The snippet was successfully updated'
    res.redirect('./../')
  } else {
    req.session.flash = 'The snippet was NOT changed, Please try again! '
    res.redirect('')
  }
}

crudController.deleteSnippetForm = async (req, res) => {
  console.log('[D] Delete snippet Form')
  const flash = req.session.flash
  req.session.flash = null
  const user = req.session.user

  if (user) {
    const snippets = await Snippet.find({ authorName: user }).catch(err => { console.error(err) })
    res.render('snippetDeleteForm', {
      flash: flash,
      snippets: snippets,
      user: user
    })
  } else {
    req.session.flash = 'You need to be connected to be able to delete snippets'
    res.redirect('./../auth/login')
  }
}
crudController.deleteSnippetProcess = async (req, res) => {
  console.log('[D] Delete snippet Process')
  const snippetId = req.body.deletingSnippet
  const snippetToDelete = await Snippet.findById(snippetId)

  if (snippetToDelete) {
    await Snippet.deleteOne({ _id: snippetId }).catch(err => { console.error(err) })
    req.session.flash = 'The snippet was successfully deleted'
    res.redirect('./../')
  } else {
    req.session.flash = 'The snippet was NOT deleted, Please try again! '
    res.redirect('')
  }
}
