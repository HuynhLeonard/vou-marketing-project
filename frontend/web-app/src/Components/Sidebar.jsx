/* eslint-disable jsx-a11y/anchor-is-valid */
import { React, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "../Styles/Sidebar.css";

function Sidebar({ selectedIndexPage }) {
    useEffect(() => {
        switch (selectedIndexPage) {
            case 0:
                document.querySelectorAll(".homepage-title").forEach((title) => {
                    title.classList.add("selected");
                });
                break;
            case 1:
                document.querySelectorAll(".userManagement-title").forEach((title) => {
                    title.classList.add("selected");
                });
                break;
            case 2:
                document.querySelectorAll(".gameManagement-title").forEach((title) => {
                    title.classList.add("selected");
                });
                break;
            case 3:
                document.querySelectorAll(".voucherAndEvent-title").forEach((title) => {
                    title.classList.add("selected");
                });
                break;
            case 4:
                document.querySelectorAll(".statistic-title").forEach((title) => {
                    title.classList.add("selected");
                });
                break;
            default:
                break;
        }
    }, [selectedIndexPage]);

    return (
        <div class="drawer font-Kanit z-50" data-theme="retro">
            <input id="my-drawer-3" type="checkbox" class="drawer-toggle" />
            <div class="drawer-content flex flex-col">
                <div class="navbar bg-base-100 w-full justify-between">
                    <div class="flex-none lg:hidden">
                        <label
                            for="my-drawer-3"
                            aria-label="open sidebar"
                            class="btn btn-square btn-ghost"
                        >
                            <svg
                                xmlns="http://www.w3.org/2000/svg"
                                fill="none"
                                viewBox="0 0 24 24"
                                class="inline-block h-6 w-6 stroke-current"
                            >
                                <path
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                    stroke-width="2"
                                    d="M4 6h16M4 12h16M4 18h16"
                                ></path>
                            </svg>
                        </label>
                    </div>
                    <div class="lg:hidden flex-none">
                        <div class="flex-1 dropdown dropdown-end flex">
                            <div class="avatar placeholder mx-2 px-2" tabIndex="0" role="button">
                                <div class="bg-red-500 text-neutral-content w-12 rounded-lg ring-red-500 ing-offset-base-100 ring ring-offset-2">
                                    <span>YuL</span>
                                </div>
                            </div>
                            <div
                                class="dropdown-content card card-compac border-2 border-red-500 shadow-2xl bg-gray-100 text-primary-content z-50 w-auto p-2 mt-20"
                                tabIndex="0"
                            >
                                <div class="card-body p-2 flex flex-col items-center">
                                    <h1 class="text-center font-bold text-2xl text-red-500">
                                        YuLockii
                                    </h1>
                                    <span class="justify-center badge badge-info">Admin</span>
                                    <div class="flex">
                                        <h3 class="text-center text-lg text-black whitespace-nowrap">
                                            Họ tên:
                                        </h3>
                                        <input
                                            type="text"
                                            placeholder="Võ Tấn Lộc"
                                            class="input input-ghost w-full text-lg text-black whitespace-nowrap p-1 h-7 placeholder-black"
                                        />
                                    </div>
                                    <div class="flex">
                                        <h3 class="text-center text-lg text-black whitespace-nowrap">
                                            Email:
                                        </h3>
                                        <input
                                            type="text"
                                            placeholder="votanloc1106@gmail.com"
                                            class="input input-ghost w-auto text-lg text-black whitespace-nowrap p-1 h-7 placeholder-black"
                                        />
                                    </div>
                                    <div class="flex">
                                        <h3 class="text-center text-lg text-black whitespace-nowrap">
                                            Số điện thoại:
                                        </h3>
                                        <input
                                            type="text"
                                            placeholder="0931416850"
                                            class="input input-ghost w-full text-lg text-black whitespace-nowrap p-1 h-7 placeholder-black"
                                        />
                                    </div>
                                    <div class="flex">
                                        <Link to="/" class="btn btn-outline btn-error m-1">
                                            Đăng xuất
                                        </Link>
                                        <Link
                                            to="/admin/editProfile"
                                            class="btn btn-outline btn-info m-1"
                                        >
                                            Chỉnh sửa thông tin
                                        </Link>
                                    </div>
                                </div>
                            </div>
                            <div class="flex flex-col ml-1 items-center">
                                <span class="text-lg">YuLockii</span>
                                <span class="justify-center badge badge-info">Admin</span>
                            </div>
                        </div>
                    </div>
                    <div class="hidden lg:block flex-none">
                        <div class="flex-1 dropdown flex">
                            <div class="avatar placeholder mx-2 px-2" tabIndex="0" role="button">
                                <div class="bg-red-500 text-neutral-content w-14 rounded-xl ring-red-500 ing-offset-base-100 ring ring-offset-2">
                                    <span>YuL</span>
                                </div>
                            </div>
                            <div
                                class="dropdown-content card card-compac border-2 border-red-500 shadow-2xl bg-gray-100 text-primary-content z-50 w-auto p-2 mt-20"
                                tabIndex="0"
                            >
                                <div class="card-body p-2 flex flex-col items-center">
                                    <h1 class="text-center font-bold text-3xl text-red-500">
                                        YuLockii
                                    </h1>
                                    <span class="justify-center badge badge-info">Admin</span>
                                    <div class="flex">
                                        <h3 class="text-center text-xl text-black whitespace-nowrap">
                                            Họ tên:
                                        </h3>
                                        <input
                                            type="text"
                                            placeholder="Võ Tấn Lộc"
                                            class="input input-ghost w-full text-xl text-black whitespace-nowrap p-1 h-7 placeholder-black"
                                        />
                                    </div>
                                    <div class="flex">
                                        <h3 class="text-center text-xl text-black whitespace-nowrap">
                                            Email:
                                        </h3>
                                        <input
                                            type="text"
                                            placeholder="votanloc1106@gmail.com"
                                            class="input input-ghost w-auto text-xl text-black whitespace-nowrap p-1 h-7 placeholder-black"
                                        />
                                    </div>
                                    <div class="flex">
                                        <h3 class="text-center text-xl text-black whitespace-nowrap">
                                            Số điện thoại:
                                        </h3>
                                        <input
                                            type="text"
                                            placeholder="0931416850"
                                            class="input input-ghost w-full text-xl text-black whitespace-nowrap p-1 h-7 placeholder-black"
                                        />
                                    </div>
                                    <div class="flex">
                                        <Link to="/" class="btn btn-outline btn-error m-2">
                                            Đăng xuất
                                        </Link>
                                        <Link
                                            to="/admin/editProfile"
                                            class="btn btn-outline btn-info m-2"
                                        >
                                            Chỉnh sửa thông tin
                                        </Link>
                                    </div>
                                </div>
                            </div>
                            <div class="flex flex-col ml-1 items-center">
                                <span class="text-xl">YuLockii</span>
                                <span class="justify-center badge badge-info">Admin</span>
                            </div>
                        </div>
                    </div>
                    <div class="hidden flex-none lg:block">
                        <ul class="menu menu-horizontal text-lg text-red-500">
                            <li class="hover:text-black">
                                <Link to="/admin/" class="homepage-title">
                                    TRANG CHỦ
                                </Link>
                            </li>
                            <li class="hover:text-black">
                                <Link to="/admin/userManagement" class="userManagement-title">
                                    QUẢN LÝ NGƯỜI DÙNG
                                </Link>
                            </li>
                            <li class="hover:text-black">
                                <Link to="/admin/gameManagement" class="gameManagement-title">
                                    QUẢN LÝ TRÒ CHƠI
                                </Link>
                            </li>
                            <li class="hover:text-black">
                                <Link to="/admin/eventManagement" class="voucherAndEvent-title">
                                    VOUCHER VÀ EVENT
                                </Link>
                            </li>
                            <li class="hover:text-black">
                                <Link to="/admin/statistic" class="statistic-title">
                                    THỐNG KÊ
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="drawer-side z-50">
                <label for="my-drawer-3" aria-label="close sidebar" class="drawer-overlay"></label>
                <ul class="menu bg-base-100 min-h-full w-1/2 p-4 text-lg text-red-500">
                    <li class="hover:text-black">
                        <Link to="/admin/" class="homepage-title">
                            TRANG CHỦ
                        </Link>
                    </li>
                    <li class="hover:text-black">
                        <Link to="/admin/userManagement" class="userManagement-title">
                            QUẢN LÝ NGƯỜI DÙNG
                        </Link>
                    </li>
                    <li class="hover:text-black">
                        <Link to="/admin/gameManagement" class="gameManagement-title">
                            QUẢN LÝ TRÒ CHƠI
                        </Link>
                    </li>
                    <li class="hover:text-black">
                        <Link to="/admin/eventManagement" class="voucherAndEvent-title">
                            VOUCHER VÀ EVENT
                        </Link>
                    </li>
                    <li class="hover:text-black">
                        <Link to="/admin/statistic" class="statistic-title">
                            THỐNG KÊ
                        </Link>
                    </li>
                </ul>
            </div>
        </div>
    );
}

export default Sidebar;
