
pragma solidity ^0.4.17;

contract Message{
address public true_sender;
uint public minimumContribution;
string description;
uint value;
uint bal;
uint prof;
uint approverCount;
uint disapproverCount;
address[] public approvers;
address[] public disapprovers;

        
	function Message(string descript)public payable
	{
		true_sender=msg.sender;
		description=descript;
		approvers.push(true_sender);
	}
	function approveMessage()public payable{
		require(msg.sender!=true_sender);
		approvers.push(msg.sender);
		approverCount++;
	}
	function disapproveMessage()public payable{
		require(msg.sender!=true_sender);
		disapprovers.push(msg.sender);
		disapproverCount++;
	}
	
	function VerifyMessage(bool validity) public payable{
		if(validity)
		{
			bal=this.balance;
			prof=bal/approverCount;
			
			for(uint i=0;i<approverCount;i++)
			{
				approvers[i].transfer(prof);
			}
		}
		else
		{
			bal=this.balance;
			prof=bal/disapproverCount;
			for(uint j=0;j<disapproverCount;j++)
			{
				disapprovers[j].transfer(prof);
			}
		}
	}

}
