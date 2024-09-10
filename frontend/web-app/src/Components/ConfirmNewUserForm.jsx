import React from "react";

function ConfirmNewUserForm({ currentProfile }) {
    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <div class="card bg-base-200 shadow-2xl w-[25%] absolute top-[30%] left-[40%] z-20">
                    <div class="card-body">
                        <div class="font-bold text-center md:text-lg xl:text-xl 2xl:text-2xl text-success">
                            Duyệt hồ sơ ?
                        </div>
                        <p class="md:text-base xl:text-lg 2xl:text-xl mb-5">
                            Thao tác này sẽ thêm hồ sơ của người dùng vào hệ thống.
                        </p>
                        <div class="card-actions justify-center">
                            <button class="btn btn-success brightness-105 text-white">
                                Duyệt ngay
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="lg:hidden">
                <div class="card bg-base-200 shadow-2xl w-[50%] absolute top-[118%] left-[27%] md:left-[25%] z-20">
                    <div class="card-body">
                        <div class="font-bold text-center text-xl md:text-2xl text-success">
                            Duyệt hồ sơ ?
                        </div>
                        <p class="text-base md:text-lg mb-5">
                            Thao tác này sẽ thêm hồ sơ của người dùng vào hệ thống.
                        </p>
                        <div class="card-actions justify-center">
                            <button class="btn btn-success brightness-105 text-white">
                                Duyệt ngay
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ConfirmNewUserForm;
