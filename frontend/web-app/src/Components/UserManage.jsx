/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useEffect, useState } from "react";
import userData from "../utils/jsonFiles/userData.json";

import ViewUserForm from "./ViewUserForm";
import ViewNewUserForm from "./ViewNewUserForm";
import RemoveUserForm from "./RemoveUserForm";
import RemoveNewUserForm from "./RemoveNewUserForm";
import EditUserForm from "./EditUserForm";
import ConfirmNewUserForm from "./ConfirmNewUserForm";
import AddNewUserForm from "./AddNewUserForm";

function UserManage() {
    const [profileData, setProfileData] = useState([]);
    const [newProfileData, setNewProfileData] = useState([]);
    const [currentProfile, setCurretProfile] = useState({});

    const [isOpenViewUser, setIsOpenViewUser] = useState(false);
    const [isOpenViewNewUser, setIsOpenViewNewUser] = useState(false);
    const [isOpenRemoveUser, setIsOpenRemoveUser] = useState(false);
    const [isOpenRemoveNewUser, setIsOpenRemoveNewUser] = useState(false);
    const [isOpenEditUser, setIsOpenEditUser] = useState(false);
    const [isOpenConfirmNewUser, setIsOpenConfirmNewUser] = useState(false);
    const [isOpenAddNewUser, setIsOpenAddNewUser] = useState(false);

    const toTitleCase = (phrase) => {
        return phrase
            .toLowerCase()
            .split(" ")
            .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
            .join(" ");
    };

    useEffect(() => {
        var userList = [];
        var newUserList = [];

        var keys = Object.keys(userData);
        keys.forEach(function (key) {
            if (userData[key].status !== "Pending") {
                userList.push(userData[key]);
            } else {
                newUserList.push(userData[key]);
            }
        });

        setProfileData(userList);
        setNewProfileData(newUserList);

        return () => {};
    }, [profileData, newProfileData, currentProfile]);

    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            {(isOpenViewUser ||
                isOpenViewNewUser ||
                isOpenRemoveUser ||
                isOpenRemoveNewUser ||
                isOpenEditUser ||
                isOpenConfirmNewUser ||
                isOpenAddNewUser) && (
                <div>
                    <div
                        class="backdrop-blur-sm w-full h-full absolute top-0 z-20 cursor-pointer"
                        onClick={() => {
                            setIsOpenViewUser(false);
                            setIsOpenViewNewUser(false);
                            setIsOpenRemoveUser(false);
                            setIsOpenRemoveNewUser(false);
                            setIsOpenEditUser(false);
                            setIsOpenConfirmNewUser(false);
                            setIsOpenAddNewUser(false);
                        }}
                    ></div>
                </div>
            )}

            {isOpenViewUser && <ViewUserForm currentProfile={currentProfile} />}
            {isOpenViewNewUser && <ViewNewUserForm currentProfile={currentProfile} />}
            {isOpenRemoveUser && <RemoveUserForm currentProfile={currentProfile} />}
            {isOpenRemoveNewUser && <RemoveNewUserForm currentProfile={currentProfile} />}
            {isOpenEditUser && <EditUserForm currentProfile={currentProfile} />}
            {isOpenConfirmNewUser && <ConfirmNewUserForm currentProfile={currentProfile} />}
            {isOpenAddNewUser && <AddNewUserForm />}

            <div class="lg:block hidden">
                <div class="flex w-full p-5 sm:p-1 sm:pt-5">
                    <div class="flex flex-col mr-2 items-center w-2/3 sm:mr-1">
                        <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                            DANH SÁCH NGƯỜI DÙNG
                        </div>
                        <div class="w-full h-[520px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            STT
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Tên tài khoản
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Email
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Vai trò
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Trạng thái
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base text-center w-[18%]">
                                            Chỉnh sửa
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {profileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    setCurretProfile(profileData[index]);
                                                    setIsOpenViewUser(true);
                                                }}
                                            >
                                                <td>{index + 1}</td>
                                                <td>{toTitleCase(obj.userName)}</td>
                                                <td>{obj.email}</td>
                                                <td>{toTitleCase(obj.role)}</td>
                                                <td>
                                                    {obj.status === "Active"
                                                        ? "Hoạt động"
                                                        : "Bị khóa"}
                                                </td>
                                                <td class="text-center">
                                                    <button
                                                        class="btn btn-sm btn-square btn-info brightness-200 m-1"
                                                        onClick={(e) => {
                                                            setCurretProfile(profileData[index]);
                                                            setIsOpenEditUser(true);
                                                            e.stopPropagation();
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-5 w-5"
                                                            viewBox="0 0 24 24"
                                                            fill="none"
                                                            stroke="#000000"
                                                            stroke-width="2"
                                                            stroke-linecap="round"
                                                            stroke-linejoin="round"
                                                        >
                                                            <polygon points="14 2 18 6 7 17 3 17 3 13 14 2"></polygon>
                                                            <line
                                                                x1="3"
                                                                y1="22"
                                                                x2="21"
                                                                y2="22"
                                                            ></line>
                                                        </svg>
                                                    </button>
                                                    <button
                                                        class="btn btn-sm btn-square btn-error brightness-105 m-1"
                                                        onClick={(e) => {
                                                            setCurretProfile(profileData[index]);
                                                            setIsOpenRemoveUser(true);
                                                            e.stopPropagation();
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-5 w-5"
                                                            fill="none"
                                                            viewBox="0 0 24 24"
                                                            stroke="currentColor"
                                                        >
                                                            <path
                                                                stroke-linecap="round"
                                                                stroke-linejoin="round"
                                                                stroke-width="2"
                                                                d="M6 18L18 6M6 6l12 12"
                                                            />
                                                        </svg>
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="text-center">
                                            <button
                                                class="btn btn-success brightness-125 w-[50%]"
                                                onClick={() => {
                                                    setIsOpenAddNewUser(true);
                                                }}
                                            >
                                                <svg
                                                    xmlns="http://www.w3.org/2000/svg"
                                                    class="w-10 h-10"
                                                    viewBox="0 0 24 24"
                                                    fill="none"
                                                    stroke="#ffffff"
                                                    stroke-width="2"
                                                    stroke-linecap="round"
                                                    stroke-linejoin="round"
                                                >
                                                    <line x1="12" y1="5" x2="12" y2="19"></line>
                                                    <line x1="5" y1="12" x2="19" y2="12"></line>
                                                </svg>
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="flex flex-col ml-2 grow items-center sm:ml-1">
                        <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                            DANH SÁCH HỒ SƠ CHỜ
                        </div>
                        <div class="w-full h-[520px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            STT
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Tên tài khoản
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Vai trò
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base text-center">
                                            Xác nhận
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {newProfileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    setCurretProfile(newProfileData[index]);
                                                    setIsOpenViewNewUser(true);
                                                }}
                                            >
                                                <td>{index + 1}</td>
                                                <td>{obj.userName}</td>
                                                <td>{toTitleCase(obj.role)}</td>
                                                <td class="text-center">
                                                    <button
                                                        class="btn btn-sm btn-square btn-success brightness-125 m-1"
                                                        onClick={(e) => {
                                                            setCurretProfile(newProfileData[index]);
                                                            setIsOpenConfirmNewUser(true);
                                                            e.stopPropagation();
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-5 w-5"
                                                            viewBox="0 0 24 24"
                                                            fill="none"
                                                            stroke="#000000"
                                                            stroke-width="2"
                                                            stroke-linecap="round"
                                                            stroke-linejoin="round"
                                                        >
                                                            <polyline points="20 6 9 17 4 12"></polyline>
                                                        </svg>
                                                    </button>
                                                    <button
                                                        class="btn btn-sm btn-square btn-error brightness-105 m-1"
                                                        onClick={(e) => {
                                                            setCurretProfile(newProfileData[index]);
                                                            setIsOpenRemoveNewUser(true);
                                                            e.stopPropagation();
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-5 w-5"
                                                            fill="none"
                                                            viewBox="0 0 24 24"
                                                            stroke="currentColor"
                                                        >
                                                            <path
                                                                stroke-linecap="round"
                                                                stroke-linejoin="round"
                                                                stroke-width="2"
                                                                d="M6 18L18 6M6 6l12 12"
                                                            />
                                                        </svg>
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="lg:hidden">
                <div class="flex flex-col w-full p-5">
                    <div class="flex flex-col items-center">
                        <div class="font-bold text-lg sm:text-xl text-info mb-5">
                            DANH SÁCH NGƯỜI DÙNG
                        </div>
                        <div class="w-full h-[500px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100 mb-5">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 text-base">STT</th>
                                        <th class="font-bold text-red-500 text-base">
                                            Tên tài khoản
                                        </th>
                                        <th class="font-bold text-red-500 text-base">Vai trò</th>
                                        <th class="font-bold text-red-500 text-base text-center">
                                            Chỉnh sửa
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {profileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    setCurretProfile(profileData[index]);
                                                    setIsOpenViewUser(true);
                                                }}
                                            >
                                                <td class="text-sm">{index + 1}</td>
                                                <td class="text-sm">{obj.userName}</td>
                                                <td class="text-sm">{toTitleCase(obj.role)}</td>
                                                <td class="text-center">
                                                    <button
                                                        class="btn btn-xs btn-square btn-info brightness-200 m-1"
                                                        onClick={(e) => {
                                                            setCurretProfile(profileData[index]);
                                                            setIsOpenEditUser(true);
                                                            e.stopPropagation();
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-4 w-4"
                                                            viewBox="0 0 24 24"
                                                            fill="none"
                                                            stroke="#000000"
                                                            stroke-width="2"
                                                            stroke-linecap="round"
                                                            stroke-linejoin="round"
                                                        >
                                                            <polygon points="14 2 18 6 7 17 3 17 3 13 14 2"></polygon>
                                                            <line
                                                                x1="3"
                                                                y1="22"
                                                                x2="21"
                                                                y2="22"
                                                            ></line>
                                                        </svg>
                                                    </button>
                                                    <button
                                                        class="btn btn-xs btn-square btn-error brightness-105 m-1"
                                                        onClick={(e) => {
                                                            setCurretProfile(profileData[index]);
                                                            setIsOpenRemoveUser(true);
                                                            e.stopPropagation();
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-4 w-4"
                                                            fill="none"
                                                            viewBox="0 0 24 24"
                                                            stroke="currentColor"
                                                        >
                                                            <path
                                                                stroke-linecap="round"
                                                                stroke-linejoin="round"
                                                                stroke-width="2"
                                                                d="M6 18L18 6M6 6l12 12"
                                                            />
                                                        </svg>
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="text-center">
                                            <button
                                                class="btn btn-success brightness-125 w-[60%]"
                                                onClick={() => {
                                                    setIsOpenAddNewUser(true);
                                                }}
                                            >
                                                <svg
                                                    xmlns="http://www.w3.org/2000/svg"
                                                    class="w-4 h-4"
                                                    viewBox="0 0 24 24"
                                                    fill="none"
                                                    stroke="#ffffff"
                                                    stroke-width="2"
                                                    stroke-linecap="round"
                                                    stroke-linejoin="round"
                                                >
                                                    <line x1="12" y1="5" x2="12" y2="19"></line>
                                                    <line x1="5" y1="12" x2="19" y2="12"></line>
                                                </svg>
                                            </button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="flex flex-col grow items-center">
                        <div class="font-bold text-lg sm:text-xl text-info mb-5">
                            DANH SÁCH HỒ SƠ CHỜ
                        </div>
                        <div class="w-full h-[500px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 text-base">STT</th>
                                        <th class="font-bold text-red-500 text-base">
                                            Tên tài khoản
                                        </th>
                                        <th class="font-bold text-red-500 text-base">Vai trò</th>
                                        <th class="font-bold text-red-500 text-base text-center">
                                            Xác nhận
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {newProfileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    setCurretProfile(newProfileData[index]);
                                                    setIsOpenViewNewUser(true);
                                                }}
                                            >
                                                <td class="text-sm">{index + 1}</td>
                                                <td class="text-sm">{obj.userName}</td>
                                                <td class="text-sm">{toTitleCase(obj.role)}</td>
                                                <td class="text-center">
                                                    <button
                                                        class="btn btn-xs btn-square btn-success brightness-125 m-1"
                                                        onClick={(e) => {
                                                            setCurretProfile(newProfileData[index]);
                                                            setIsOpenConfirmNewUser(true);
                                                            e.stopPropagation();
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-4 w-4"
                                                            viewBox="0 0 24 24"
                                                            fill="none"
                                                            stroke="#000000"
                                                            stroke-width="2"
                                                            stroke-linecap="round"
                                                            stroke-linejoin="round"
                                                        >
                                                            <polyline points="20 6 9 17 4 12"></polyline>
                                                        </svg>
                                                    </button>
                                                    <button
                                                        class="btn btn-xs btn-square btn-error brightness-105 m-1"
                                                        onClick={(e) => {
                                                            setCurretProfile(newProfileData[index]);
                                                            setIsOpenRemoveNewUser(true);
                                                            e.stopPropagation();
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-4 w-4"
                                                            fill="none"
                                                            viewBox="0 0 24 24"
                                                            stroke="currentColor"
                                                        >
                                                            <path
                                                                stroke-linecap="round"
                                                                stroke-linejoin="round"
                                                                stroke-width="2"
                                                                d="M6 18L18 6M6 6l12 12"
                                                            />
                                                        </svg>
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default UserManage;
