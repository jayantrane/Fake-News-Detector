const HDWalletProvider=require('truffle-hdwallet-provider');
const Web3=require('web3');
const message=require('./build/Message.json');


const provider=new HDWalletProvider(
	'flame monitor nature grunt unusual raven chat uncover destroy plunge follow label',
	'https://rinkeby.infura.io/v3/3b47b115de694018ab1a8c4cf6f287db'
	);
const web3=new Web3(provider);

const deploy=async()=>{
const accounts=await web3.eth.getAccounts();
console.log('Attempting to deploy from account',accounts[0]);

const result=await new web3.eth.Contract(JSON.parse(message.interface))
.deploy({data:message.bytecode})
.send({gas:'1000000',from:accounts[0],value:'10000000'});
console.log('Contract deployed to',result.options.address);
};
deploy();