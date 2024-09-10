import React from "react";
import { Link } from "react-router-dom";

function RemoveNewUserForm() {
    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <Link
                    to="/admin/userManagement"
                    class="backdrop-blur-sm w-full h-full absolute top-0 z-20 cursor-pointer"
                ></Link>
                <div class="card bg-base-200 shadow-2xl w-[25%] absolute top-[30%] left-[40%] z-20">
                    <div class="card-body">
                        <div class="font-bold text-center md:text-lg xl:text-xl 2xl:text-2xl text-red-500">
                            Xóa hồ sơ ?
                        </div>
                        <p class="md:text-base xl:text-lg 2xl:text-xl mb-5">
                            Thao tác này sẽ vĩnh viễn xóa hồ sơ của người dùng.
                        </p>
                        <div class="card-actions justify-center">
                            <Link
                                to="/admin/userManagement"
                                class="btn btn-error brightness-105 text-white"
                            >
                                Xóa ngay
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
            <div class="lg:hidden">
                <Link
                    to="/admin/userManagement"
                    class="backdrop-blur-sm w-full h-[1000px] absolute top-0 z-20 cursor-pointer"
                ></Link>
                <div class="card bg-base-200 shadow-2xl w-[50%] absolute top-[118%] left-[27%] md:left-[25%] z-20">
                    <div class="card-body">
                        <div class="font-bold text-center text-xl md:text-2xl text-red-500">
                            Xóa hồ sơ ?
                        </div>
                        <p class="text-base md:text-lg mb-5">
                            Thao tác này sẽ vĩnh viễn xóa hồ sơ của người dùng.
                        </p>
                        <div class="card-actions justify-center">
                            <Link
                                to="/admin/userManagement"
                                class="btn btn-error brightness-105 text-white"
                            >
                                Xóa ngay
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default RemoveNewUserForm;
