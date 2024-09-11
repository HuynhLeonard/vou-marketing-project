import React from "react";
import avatar from "../utils/images/avatar.png";

function ViewUserForm({ currentProfile }) {
    const toTitleCase = (phrase) => {
        return phrase
            .toLowerCase()
            .split(" ")
            .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
            .join(" ");
    };

    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <div class="card lg:card-side bg-base-200 shadow-2xl sm:w-[65%] xl:w-[55%] 2xl:w-[55%] absolute top-[20%] sm:left-[22%] xl:left-[24%] 2xl:left-[24%] z-20">
                    <figure class="w-1/2">
                        <img class="object-cover" src={currentProfile.avatarUrl ? currentProfile.avatarUrl : avatar} alt="Album" />
                    </figure>
                    <div class="card-body">
                        <div class="flex flex-col items-center mb-5">
                            <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-red-500">
                                {currentProfile === undefined ? "" : currentProfile.username}
                            </div>
                            <div class="badge badge-info">
                                {currentProfile === undefined
                                    ? ""
                                    : toTitleCase(currentProfile.role)}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Họ và tên:&nbsp;</div>
                            <div>
                                {currentProfile === undefined
                                    ? ""
                                    : toTitleCase(currentProfile.fullName)}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Email:&nbsp;</div>
                            <div>{currentProfile === undefined ? "" : currentProfile.email}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Điện thoại:&nbsp;</div>
                            <div>
                                {currentProfile === undefined ? "" : currentProfile.phoneNumber}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Giới tính:&nbsp;</div>
                            <div>
                                {currentProfile === null
                                    ? ""
                                    : currentProfile.gender === "male"
                                    ? "Nam"
                                    : "Nữ"}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Trạng thái:&nbsp;</div>
                            <div>
                                {currentProfile === undefined
                                    ? ""
                                    : currentProfile.status === "ACTIVE"
                                    ? "Hoạt động"
                                    : (currentProfile.status === "PENDING" ? "Đang chờ" : "Bị khóa")}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Facebook:&nbsp;</div>
                            {currentProfile === undefined ? "" : currentProfile.accountFacebook}
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Ngày sinh:&nbsp;</div>
                            {currentProfile === undefined ? "" : currentProfile.dayOfBirth}
                        </div>
                    </div>
                </div>
            </div>
            <div class="lg:hidden">
                <div class="card card-side bg-base-200 shadow-2xl max-w-[60%] absolute top-[35%] sm:top-[30%] md:top-[28%] left-[20%] z-20">
                    <div class="card-body p-0">
                        <div class="flex w-full items-center">
                            <figure class="w-1/2">
                                <img class="object-cover" src={avatar} alt="Album" />
                            </figure>
                            <div class="flex flex-col items-center w-1/2">
                                <div class="font-bold text-lg sm:text-xl md:text-2xl text-red-500">
                                    {currentProfile === undefined ? "" : currentProfile.userName}
                                </div>
                                <div class="badge badge-info">
                                    {currentProfile === undefined
                                        ? ""
                                        : toTitleCase(currentProfile.role)}
                                </div>
                            </div>
                        </div>
                        <div class="p-4 flex flex-col sm:items-center">
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Họ và tên:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined
                                        ? ""
                                        : toTitleCase(currentProfile.fullName)}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Email:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined ? "" : currentProfile.email}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Điện thoại:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined ? "" : currentProfile.phoneNumber}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Giới tính:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined
                                        ? ""
                                        : currentProfile.gender === "male"
                                        ? "Nam"
                                        : "Nữ"}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Trạng thái:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined
                                        ? ""
                                        : currentProfile.status === "Active"
                                        ? "Hoạt động"
                                        : "Bị khóa"}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Facebook:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined
                                        ? ""
                                        : currentProfile.accountFacebook}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Ngày sinh:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined ? "" : currentProfile.dayOfBirth}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ViewUserForm;
