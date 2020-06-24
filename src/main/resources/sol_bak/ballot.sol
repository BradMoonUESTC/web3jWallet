// ["0xa000","0xb000","0xc000","0xd000"]
pragma solidity ^0.5.10;
//import "github.com/Arachnid/solidity-stringutils/strings.sol";
contract Ballot {
    // 这里声明了一个新的复合类型用于稍后的变量
    // 它用来表示一个选民
    struct Voter {
        uint weight; // 计票的权重
        bool voted;  // 若为真，代表该人已投票
        address delegate; // 被委托人
        uint vote;   // 投票提案的索引
    }

    // 提案的类型
    struct Proposal {
        bytes2 name;   // 简称（最长2个字节）
        uint voteCount; // 得票数
    }

    address public chairperson;

    string public ret;

    // 这声明了一个状态变量，为每个可能的地址存储一个 `Voter`。
    mapping(address => Voter) public voters;

    // 一个 `Proposal` 结构类型的动态数组
    Proposal[] public proposals;

    event event_createproposal(bytes2[4] _proposalNames,address _proposaler);
    event event_giveright(address _voter);
    event event_voteaction(uint _proposal,address _voter);

    /// 为 `proposalNames` 中的每个提案，创建一个新的（投票）表决
    constructor() public {
        bytes2[4] memory proposalNames= [bytes2(0xa000),bytes2(0xb000),bytes2(0xc000),bytes2(0xd000)];
        chairperson = msg.sender;
        voters[chairperson].weight = 1;
        emit event_createproposal(proposalNames,chairperson);
        //对于提供的每个提案名称，
        //创建一个新的 Proposal 对象并把它添加到数组的末尾。
        for (uint i = 0; i < proposalNames.length; i++) {
            proposals.push(Proposal({
                name: proposalNames[i],
                voteCount: 0
                }));
        }
    }

    // 授权 `voter` 对这个（投票）表决进行投票
    // 只有 `chairperson` 可以调用该函数。
    function giveRightToVote(address voter) public {
        require(
            msg.sender == chairperson,
            "只有主席才能授权投票 "
        );
        require(
            !voters[voter].voted,
            "被授权者已经投过票了 "
        );
        require(voters[voter].weight == 0,"此人已经有投票权了，不能重复赋权");
        voters[voter].weight = 1;
        emit event_giveright(voter);

    }

    function vote(uint proposal) public {
        Voter storage sender = voters[msg.sender];
        require(!sender.voted, "你已经投过票了");
        sender.voted = true;
        sender.vote = proposal;
        proposals[proposal].voteCount += sender.weight;
        emit event_voteaction(proposal,msg.sender);

    }

    /// @dev 结合之前所有的投票，计算出最终胜出的提案
    function winningProposal() public view
    returns (uint winningProposal_)
    {
        uint winningVoteCount = 0;
        for (uint p = 0; p < proposals.length; p++) {
            if (proposals[p].voteCount > winningVoteCount) {
                winningVoteCount = proposals[p].voteCount;
                winningProposal_ = p;
            }
        }
    }

    // 调用 winningProposal() 函数以获取提案数组中获胜者的索引，并以此返回获胜者的名称
    function winnerName() public view
    returns (bytes2 winnerName_)
    {
        winnerName_ = proposals[winningProposal()].name;
    }
}
