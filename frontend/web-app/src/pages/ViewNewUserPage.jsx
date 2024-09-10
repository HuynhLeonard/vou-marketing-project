import React from "react";
import Sidebar from "../Components/Sidebar";
import ViewNewUserForm from "../Components/ViewNewUserForm";
import UserManage from "../Components/UserManage";

function ViewNewUserPage() {
    return (
        <div>
            <Sidebar selectedIndexPage={1} />
            <ViewNewUserForm />
            <UserManage />
        </div>
    );
}

export default ViewNewUserPage;
