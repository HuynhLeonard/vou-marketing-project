import React, { useEffect, useState } from "react";
import { Carousel } from "flowbite-react";
import { useMutation } from "react-query";
import userManageImage from "../utils/images/userManage.png";
import gameManageImage from "../utils/images/gameManage.png";
import voucherManageImage from "../utils/images/voucherManage.png";
import statisticManageImage from "../utils/images/statisticManage.png";

import api from "../service/api";
import axios from "axios";

import eventDashboard from "../utils/images/eventDashboard.png";

import { Link } from "react-router-dom";

function Homepage() {
    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <div class="flex w-full p-5">
                    <div class="flex flex-col mr-2 items-center w-2/3">
                        <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                            THÔNG SỐ TỔNG QUÁT
                        </div>
                        <div class="grid grid-cols-2 w-full h-[500px] gap-4 ">
                            <div class="card bg-base-100 flex">
                                <img
                                    class="object-cover w-1/3"
                                    src={eventDashboard}
                                    alt="Event Dashboard"
                                />
                                <div class="card-body">
                                    <div class="stat">
                                        <div class="stat-title font-bold">Tổng số người dùng</div>
                                        <div class="stat-value text-red-500">150</div>
                                    </div>
                                </div>
                            </div>
                            <div class="card bg-base-100">
                                <div class="card-body">
                                    <div class="stat">
                                        <div class="stat-figure text-primary">
                                            <svg
                                                xmlns="http://www.w3.org/2000/svg"
                                                fill="none"
                                                viewBox="0 0 24 24"
                                                class="inline-block h-8 w-8 stroke-current"
                                            >
                                                <path
                                                    stroke-linecap="round"
                                                    stroke-linejoin="round"
                                                    stroke-width="2"
                                                    d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"
                                                ></path>
                                            </svg>
                                        </div>
                                        <div class="stat-title">Total Likes</div>
                                        <div class="stat-value text-primary">25.6K</div>
                                        <div class="stat-desc">21% more than last month</div>
                                    </div>
                                </div>
                            </div>
                            <div class="card bg-base-100">
                                <div class="card-body">
                                    <div class="stat">
                                        <div class="stat-figure text-primary">
                                            <svg
                                                xmlns="http://www.w3.org/2000/svg"
                                                fill="none"
                                                viewBox="0 0 24 24"
                                                class="inline-block h-8 w-8 stroke-current"
                                            >
                                                <path
                                                    stroke-linecap="round"
                                                    stroke-linejoin="round"
                                                    stroke-width="2"
                                                    d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"
                                                ></path>
                                            </svg>
                                        </div>
                                        <div class="stat-title">Total Likes</div>
                                        <div class="stat-value text-primary">25.6K</div>
                                        <div class="stat-desc">21% more than last month</div>
                                    </div>
                                </div>
                            </div>
                            <div class="card bg-base-100">
                                <div class="card-body">
                                    <div class="stat">
                                        <div class="stat-figure text-primary">
                                            <svg
                                                xmlns="http://www.w3.org/2000/svg"
                                                fill="none"
                                                viewBox="0 0 24 24"
                                                class="inline-block h-8 w-8 stroke-current"
                                            >
                                                <path
                                                    stroke-linecap="round"
                                                    stroke-linejoin="round"
                                                    stroke-width="2"
                                                    d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z"
                                                ></path>
                                            </svg>
                                        </div>
                                        <div class="stat-title">Total Likes</div>
                                        <div class="stat-value text-primary">25.6K</div>
                                        <div class="stat-desc">21% more than last month</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="flex flex-col ml-2 grow items-center">
                        <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                            CÁC TRANG QUẢN LÝ
                        </div>
                        <Carousel
                            pauseOnHover
                            class="w-full h-full z-10"
                            leftControl=" "
                            rightControl=" "
                            indicators="false"
                        >
                            <Link to="/admin/userManagement">
                                <img
                                    src={userManageImage}
                                    alt="User Management"
                                    className="bg-info-content"
                                />
                            </Link>
                            <Link to="/admin/gameManagement">
                                <img
                                    src={gameManageImage}
                                    alt="Game Management"
                                    className="bg-red-400"
                                />
                            </Link>
                            <Link to="/admin/eventManagement">
                                <img
                                    src={voucherManageImage}
                                    alt="Voucher Management"
                                    className="bg-info-content"
                                />
                            </Link>
                            <Link to="/admin/statistic">
                                <img
                                    src={statisticManageImage}
                                    alt="Statistic Management"
                                    className="bg-red-400"
                                />
                            </Link>
                        </Carousel>
                    </div>
                </div>
            </div>
            <div class="lg:hidden">
                <div class="flex flex-col p-5">
                    <div class="flex flex-col items-center w-full">
                        <div class="font-bold text-lg sm:text-xl text-info mb-5">
                            THÔNG BÁO NGÀY HÔM NAY
                        </div>
                        <div class="stats shadow mb-5 w-full text-sm">
                            <div class="stat">
                                <div class="stat-title text-black">Lượt tải về</div>
                                <div class="stat-value text-red-400">2.8K</div>
                                <div class="stat-desc">↗︎ 1.2K (22%)</div>
                            </div>

                            <div class="stat">
                                <div class="stat-title text-black">Lượt yêu thích</div>
                                <div class="stat-value text-red-400">4.2K</div>
                                <div class="stat-desc">↗︎ 800 (6%)</div>
                            </div>
                        </div>

                        <div class="stats shadow mb-5 w-full text-sm">
                            <div class="stat">
                                <div class="stat-title text-black">Tổng người dùng</div>
                                <div class="stat-value text-red-400">12.6K</div>
                            </div>

                            <div class="stat">
                                <div class="stat-title text-black">Tổng trò chơi</div>
                                <div class="stat-value text-red-400">2</div>
                            </div>

                            <div class="stat">
                                <div class="stat-title text-black">Tổng voucher</div>
                                <div class="stat-value text-red-400">30</div>
                            </div>
                        </div>

                        <div class="stats bg-base-100 text-primary-content w-full text-sm mb-5">
                            <div class="stat">
                                <div>
                                    <div class="stat-title text-black">Người dùng mới</div>
                                    <div class="stat-value text-red-400">5.6K</div>
                                </div>
                                <div class="stat-actions">
                                    <Link
                                        to="/admin/userManagement"
                                        class="btn btn-sm bg-info text-white w-full"
                                    >
                                        Duyệt ngay
                                    </Link>
                                </div>
                            </div>
                            <div class="stat">
                                <div>
                                    <div class="stat-title text-black">Tổng doanh thu</div>
                                    <div class="stat-value text-red-400">$15.400K</div>
                                </div>
                                <div class="stat-actions">
                                    <Link
                                        to="/admin/statistic"
                                        class="btn btn-sm bg-info text-white w-full"
                                    >
                                        Xem thống kê
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="flex flex-col items-center justify-center">
                        <div class="font-bold text-xl sm:text-2xl text-info mb-5 sm:mb-0">
                            CÁC TRANG QUẢN LÝ
                        </div>
                        <Carousel
                            pauseOnHover
                            class="w-full h-[450px] sm:w-1/2 z-10"
                            leftControl=" "
                            rightControl=" "
                            indicators={false}
                        >
                            <Link to="/admin/userManagement">
                                <img
                                    src={userManageImage}
                                    alt="User Management"
                                    className="bg-info-content"
                                />
                            </Link>
                            <Link to="/admin/gameManagement">
                                <img
                                    src={gameManageImage}
                                    alt="Game Management"
                                    className="bg-red-400"
                                />
                            </Link>
                            <Link to="/admin/eventManagement">
                                <img
                                    src={voucherManageImage}
                                    alt="Voucher Management"
                                    className="bg-info-content"
                                />
                            </Link>
                            <Link to="/admin/statistic">
                                <img
                                    src={statisticManageImage}
                                    alt="Statistic Management"
                                    className="bg-red-400"
                                />
                            </Link>
                        </Carousel>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Homepage;
