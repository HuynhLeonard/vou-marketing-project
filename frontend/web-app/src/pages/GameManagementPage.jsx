import React from "react";
import GameManage from "../Components/GameManage";
import Navigation from "../Components/Navigation";
import NavigationTwo from "../Components/NavigationTwo";
import Sidebar from "../Components/Sidebar";

const GameManagementPage = () => {
    return (
        <div>
            <Sidebar selectedIndexPage={2} />
            <GameManage />
        </div>
    );
};

export default GameManagementPage;
