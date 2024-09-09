import React from "react";
import Navigation from "../Components/Navigation";
import Statistics from "../Components/Statistics";
import NavigationTwo from "../Components/NavigationTwo";
import Sidebar from "../Components/Sidebar";

const StatisticPage = () => {
    return (
        <div>
            <Sidebar selectedIndexPage={4} />
            <Statistics />
        </div>
    );
};

export default StatisticPage;
