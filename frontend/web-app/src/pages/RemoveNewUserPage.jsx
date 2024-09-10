import React from "react";
import Sidebar from "../Components/Sidebar";
import UserManage from "../Components/UserManage";
import RemoveNewUserForm from "../Components/RemoveNewUserForm";

function RemoveNewUserPage() {
    return (
        <div>
            <Sidebar selectedIndexPage={1} />
            <RemoveNewUserForm />
            <UserManage />
        </div>
    );
}

export default RemoveNewUserPage;
