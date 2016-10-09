var seneca = require('seneca')()
seneca.add({role:'math', cmd:'power'}, function (msg, respond) {
	var result = Math.pow(msg.A, msg.B) + Math.pow(msg.C, msg.D)
	respond(null, {answer:result})
})
seneca.act({role: 'math', cmd: 'power', A: 1, B: 2, C: 3, D: 4}, console.log)