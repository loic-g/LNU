const User = require('../models/user')

const lController = {}
module.exports = lController

lController.welcome = (req, res) => {
  const flash = req.session.flash
  req.session.flash = null

  res.render('welcome', {
    flash: flash,
    user: req.session.user,
    session: JSON.stringify(req.session, null, 2)
  })
}
// LOGIN PART
lController.loginForm = (req, res) => {
  const flash = req.session.flash
  req.session.flash = null

  res.render('loginForm', {
    flash: flash,
    user: req.session.user
  })
}

lController.loginProcess = async (req, res) => {
  const user = req.body.user
  const pw = req.body.password
  const currentUser = await User.findOne({ username: user }).catch(err => {
    console.error(err)
  })

  if (currentUser && currentUser.password === pw) {
    req.session.user = user
    req.session.flash = `You are connected as '${user}'`
    res.redirect('/')
  } else {
    req.session.flash = 'You failed to AUTH! Try again!'
    res.redirect('./login')
  }
}

// REGISTER PART
lController.registerForm = (req, res) => {
  const flash = req.session.flash
  req.session.flash = null

  res.render('registerForm', {
    flash: flash,
    user: req.session.user
  })
}
lController.registerProcess = async (req, res) => {
  if (await User.exists({ username: req.body.user })) {
    req.session.flash = 'Username already exist!'
    res.redirect('./../auth/register')
  } else {
    const user = await User.create({
      username: req.body.user,
      password: req.body.password
    }).catch(err => {
      console.log('ERROR with creating user: ' + err)
    })
    if (user) {
      req.session.user = user.username
      req.session.flash = 'Successful Register'
      res.redirect('/')
    }
  }
}

// LOGOUT PART
lController.logoutForm = (req, res) => {
  res.render('logoutForm', { user: req.session.user })
}

lController.logoutProcess = (req, res) => {
  req.session.user = null
  req.session.flash = 'You are now logged out!'
  res.redirect('./login')
}
