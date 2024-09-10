import React from "react";

function RemoveUserForm() {
    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <div class="card bg-base-200 shadow-2xl w-[25%] absolute top-[30%] left-[40%] z-20">
                    <div class="card-body">
                        <div class="font-bold text-center md:text-lg xl:text-xl 2xl:text-2xl text-red-500">
                            Xóa người dùng ?
                        </div>
                        <p class="md:text-base xl:text-lg 2xl:text-xl mb-5">
                            Thao tác này sẽ vĩnh viễn xóa thông tin của người dùng.
                        </p>
                        <div class="card-actions justify-center">
                            <button class="btn btn-error brightness-105 text-white">
                                Xóa ngay
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="lg:hidden">
                <div class="card bg-base-200 shadow-2xl w-[50%] absolute top-[38%] left-[27%] md:left-[25%] z-20">
                    <div class="card-body">
                        <div class="font-bold text-center text-xl md:text-2xl text-red-500">
                            Xóa người dùng ?
                        </div>
                        <p class="text-base md:text-lg mb-5">
                            Thao tác này sẽ vĩnh viễn xóa thông tin của người dùng.
                        </p>
                        <div class="card-actions justify-center">
                            <button class="btn btn-error brightness-105 text-white">
                                Xóa ngay
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default RemoveUserForm;
