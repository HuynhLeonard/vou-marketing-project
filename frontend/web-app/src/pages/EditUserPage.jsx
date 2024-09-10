import React from "react";
import Sidebar from "../Components/Sidebar";
import EditUserForm from "../Components/EditUserForm";
import UserManage from "../Components/UserManage";

function EditUserPage() {
    return (
        <div>
            <Sidebar selectedIndexPage={1} />
            <EditUserForm />
            <UserManage />
        </div>
    );
}

export default EditUserPage;
