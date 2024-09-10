import React from "react";
import Sidebar from "../Components/Sidebar";
import ViewUserForm from "../Components/ViewUserForm";
import UserManage from "../Components/UserManage";

function ViewUserPage() {
    return (
        <div>
            <Sidebar selectedIndexPage={1} />
            <ViewUserForm />
            <UserManage />
        </div>
    );
}

export default ViewUserPage;
