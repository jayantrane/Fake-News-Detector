const solc = require('solc');
const path = require('path');
const fs = require('fs-extra');


console.log("1");

const buildPath = path.resolve(__dirname, 'build');
fs.removeSync(buildPath);



console.log('build path found');

const messagePath = path.resolve(__dirname, 'contracts', 'Message.sol');
console.log("2.1");
const source = fs.readFileSync(messagePath, 'utf8');
console.log("2.2");
const output = solc.compile(source, 1).contracts[':Message'];
console.log(output)
console.log("2.3");

fs.ensureDirSync(buildPath);

console.log('file outputed');
for (let contract in output) {
	fs.outputJsonSync(
		path.resolve(buildPath, contract.replace(':','') + '.json'),
		output[contract]
		);
}
console.log('compile');
module.exports=solc.compile(source,1).contracts[':Message'];