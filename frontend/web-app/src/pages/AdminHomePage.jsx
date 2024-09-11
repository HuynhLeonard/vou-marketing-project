import React, { useEffect } from "react";
import Navigation from "../Components/Navigation";
import Homepage from "../Components/Homepage";
import Sidebar from "../Components/Sidebar";
import NavigationTwo from "../Components/NavigationTwo";
import AdminEditAccountForm from "../Components/AdminForm/EditAccountForm";
import AdminAddAccountForm from "../Components/AdminForm/AddAccountForm";
import axios from "axios";
import api from "../service/api";

const AdminHomePage = () => {
    // const handleUser = async () => {
    //     await api
    //         .get("http://localhost:8082/api/v1/users/hthuubrand")
    //         .then((data) => console.log(data));
    // };

    // const handleLogin = async () => {
    //     await api
    //         .post("http://localhost:8081/api/v1/auth/login", {
    //             username: "testplayer",
    //             password: "Thienhuu@2003",
    //         })
    //         .then((data) => {
    //             console.log(data);
    //         });
    // };

    // handleLogin();
    // handleUser();

    const categoryData = [
        { name: "TRANG CHỦ", navigation: "/admin", title: "homepage-title" },
        {
            name: "QUẢN LÝ NGƯỜI DÙNG",
            navigation: "/admin/userManagement",
            title: "userManagement-title",
        },
        {
            name: "QUẢN LÝ TRÒ CHƠI",
            navigation: "/admin/gameManagement",
            title: "gameManagement-title",
        },
        {
            name: "QUẢN LÝ EVENT",
            navigation: "/admin/eventManagement",
            title: "voucherAndEvent-title",
        },
        { name: "THỐNG KÊ", navigation: "/admin/statistic", title: "statistic-title" },
    ];

    return (
        <div>
            <Sidebar selectedIndexPage={0} categoryData={categoryData} />
            <Homepage />
        </div>
    );
};

export default AdminHomePage;
