var seneca = require('seneca')()
seneca.add({role:'math', cmd:'sum'}, (msg, respond) => {
	var sum = msg.left + msg.right
	respond(null, {answer:sum})
})
seneca.act({role: 'math', cmd: 'sum', left: 1.5, right: 2.5}, console.log)