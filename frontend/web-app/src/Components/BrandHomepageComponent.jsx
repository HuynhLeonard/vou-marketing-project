import React from "react";
import { Link } from "react-router-dom";
import { Carousel } from "flowbite-react";

import userManageImage from "../utils/images/userManage.png";
import gameManageImage from "../utils/images/gameManage.png";
import voucherManageImage from "../utils/images/voucherManage.png";
import statisticManageImage from "../utils/images/statisticManage.png";

import eventDashboard from "../utils/images/eventDashboard.png";
import userDashboard from "../utils/images/userDashboard.png";
import brandDashboard from "../utils/images/brandDashboard.png";
import voucherDashboard from "../utils/images/voucherDashboard.png";

function BrandHomepageComponent() {
    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <div class="flex w-full p-5">
                    <div class="flex flex-col mr-2 items-center w-2/3">
                        <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                            THÔNG SỐ TỔNG QUÁT
                        </div>
                        <div class="grid grid-cols-2 w-full h-[500px] gap-4">
                            <Link
                                to="/admin/userManagement"
                                class="bg-base-100 flex rounded-2xl shadow-2xl cursor-pointer"
                            >
                                <img
                                    class="object-cover w-1/3 bg-blue-600"
                                    src={userDashboard}
                                    alt="User Dashboard"
                                />
                                <div class="card-body md:pl-0">
                                    <div class="stat">
                                        <div class="stat-title font-bold">Tổng số người dùng</div>
                                        <div class="stat-value text-blue-600 text-8xl">150</div>
                                    </div>
                                </div>
                            </Link>
                            <Link
                                to="/admin/userManagement"
                                class="bg-base-100 flex rounded-2xl shadow-2xl cursor-pointer"
                            >
                                <img
                                    class="object-cover w-1/3 bg-red-600"
                                    src={brandDashboard}
                                    alt="Brand Dashboard"
                                />
                                <div class="card-body md:pl-0">
                                    <div class="stat">
                                        <div class="stat-title font-bold">Tổng số nhãn hàng</div>
                                        <div class="stat-value text-red-600 text-8xl">10</div>
                                    </div>
                                </div>
                            </Link>
                            <Link
                                to="/admin/voucherAndEvent"
                                class="bg-base-100 flex rounded-2xl shadow-2xl cursor-pointer"
                            >
                                <img
                                    class="object-cover w-1/3 bg-green-600"
                                    src={eventDashboard}
                                    alt="Event Dashboard"
                                />
                                <div class="card-body md:pl-0">
                                    <div class="stat">
                                        <div class="stat-title font-bold">Tổng số sự kiện</div>
                                        <div class="stat-value text-green-600 text-8xl">18</div>
                                    </div>
                                </div>
                            </Link>
                            <Link
                                to="/admin/voucherAndEvent"
                                class="bg-base-100 flex rounded-2xl shadow-2xl cursor-pointer"
                            >
                                <img
                                    class="object-cover w-1/3 bg-yellow-600"
                                    src={voucherDashboard}
                                    alt="Voucher Dashboard"
                                />
                                <div class="card-body md:pl-0">
                                    <div class="stat">
                                        <div class="stat-title font-bold">Tổng số voucher</div>
                                        <div class="stat-value text-yellow-600 text-8xl">80</div>
                                    </div>
                                </div>
                            </Link>
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
            <div class="lg:hidden">
                <div class="flex flex-col p-5">
                    <div class="flex flex-col items-center w-full">
                        <div class="font-bold text-lg sm:text-xl text-info mb-5">
                            THÔNG BÁO NGÀY HÔM NAY
                        </div>
                        <div class="grid grid-cols-2 w-full h-[500px] gap-4 mb-5">
                            <Link
                                to="/admin/userManagement"
                                class="bg-base-100 flex rounded-2xl shadow-2xl cursor-pointer"
                            >
                                <div class="stat">
                                    <div class="stat-title font-bold">Tổng số người dùng</div>
                                    <div class="stat-value text-blue-600 text-8xl">150</div>
                                </div>
                            </Link>
                            <Link
                                to="/admin/userManagement"
                                class="bg-base-100 flex rounded-2xl shadow-2xl cursor-pointer"
                            >
                                <div class="stat">
                                    <div class="stat-title font-bold">Tổng số nhãn hàng</div>
                                    <div class="stat-value text-red-600 text-8xl">10</div>
                                </div>
                            </Link>
                            <Link
                                to="/admin/voucherAndEvent"
                                class="bg-base-100 flex rounded-2xl shadow-2xl cursor-pointer"
                            >
                                <div class="stat">
                                    <div class="stat-title font-bold">Tổng số sự kiện</div>
                                    <div class="stat-value text-green-600 text-8xl">18</div>
                                </div>
                            </Link>
                            <Link
                                to="/admin/voucherAndEvent"
                                class="bg-base-100 flex rounded-2xl shadow-2xl cursor-pointer"
                            >
                                <div class="stat">
                                    <div class="stat-title font-bold">Tổng số voucher</div>
                                    <div class="stat-value text-yellow-600 text-8xl">80</div>
                                </div>
                            </Link>
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

export default BrandHomepageComponent;
