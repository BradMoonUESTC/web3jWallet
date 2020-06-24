pragma solidity ^0.5.10;

contract event_test{

    event senddata(address,string);
    function emitdata() public {
        emit senddata(msg.sender, "evet_data_test");

    }

}
