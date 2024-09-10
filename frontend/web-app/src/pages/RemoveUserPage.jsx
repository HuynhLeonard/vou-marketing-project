import React from "react";
import Sidebar from "../Components/Sidebar";
import UserManage from "../Components/UserManage";
import RemoveUserForm from "../Components/RemoveUserForm";

function RemoveUserPage() {
    return (
        <div>
            <Sidebar selectedIndexPage={1} />
            <RemoveUserForm />
            <UserManage />
        </div>
    );
}

export default RemoveUserPage;
