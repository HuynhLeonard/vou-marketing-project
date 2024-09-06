import React, { useEffect, useState } from "react";
import "./App.css";
import Sidebar from "./Components/Sidebar.jsx";
import Homepage from "./Components/Homepage.jsx";
import UserManage from "./Components/UserManage.jsx";
import GameManage from "./Components/GameManage.jsx";
import VoucherManage from "./Components/VoucherManage.jsx";
import Statistics from "./Components/Statistics.jsx";

function App() {
    const [selectedIndex, setSelected] = useState(0);

    useEffect(() => {
        return () => {};
    }, [selectedIndex]);

    const RenderComponent = ({ index }) => {
        switch (index) {
            case 0:
                return <Homepage setSelected={setSelected} />;
            case 1:
                return <UserManage />;
            case 2:
                return <GameManage />;
            case 3:
                return <VoucherManage />;
            case 4:
                return <Statistics />;
            default:
                return <Homepage />;
        }
    };

    return (
        <div>
            <Sidebar setSelected={setSelected} />
            <RenderComponent index={selectedIndex} />
        </div>
    );
}

export default App;
