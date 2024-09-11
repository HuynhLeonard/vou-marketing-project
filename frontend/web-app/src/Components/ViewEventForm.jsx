import React, { useEffect } from "react";
import avatar from "../utils/images/avatar.png";
import { convertDataToOutputString } from "../utils/date";


function ViewEventForm({ currentProfile }) {
    const toTitleCase = (phrase) => {
        return phrase
            .toLowerCase()
            .split(" ")
            .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
            .join(" ");
    };

    useEffect(() => {
        console.log(currentProfile);
    }, [currentProfile]);

    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <div class="card lg:card-side bg-base-200 shadow-2xl sm:w-[65%] xl:w-[55%] 2xl:w-[55%] absolute top-[20%] sm:left-[22%] xl:left-[24%] 2xl:left-[24%] z-20">
                    <figure class="w-1/2">
                        <img
                            class="object-cover"
                            src={currentProfile.imageUrl}
                            alt="Album"
                        />
                    </figure>
                    <div class="card-body">
                        <div class="flex flex-col items-center mb-5">
                            <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-red-500">
                                {currentProfile === undefined
                                    ? ""
                                    : (currentProfile.eventName)}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Số lượt chia sẻ:&nbsp;</div>
                            <div>{currentProfile.shareCount ? currentProfile.shareCount : 0}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Tổng số voucher:&nbsp;</div>
                            <div>
                                {currentProfile === undefined ? "" : currentProfile.numberOfVouchers}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Số voucher còn lại:&nbsp;</div>
                            <div>
                                {currentProfile === undefined ? "" : currentProfile.remainingVouchers}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Ngày bắt đầu:&nbsp;</div>
                            {convertDataToOutputString(currentProfile.startDate)}
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Ngày kết thúc:&nbsp;</div>
                            {convertDataToOutputString(currentProfile.endDate)}
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
                                    {currentProfile === undefined
                                        ? ""
                                        : (currentProfile.name)}
                                </div>
                            </div>
                        </div>
                        <div class="p-4 flex flex-col sm:items-center">
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Số lượt chia sẻ:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined ? "" : currentProfile.shareNum}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Tổng số voucher:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined ? "" : currentProfile.voucherNum}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Số voucher còn lại:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined ? "" : currentProfile.voucherLeft}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Ngày bắt đầu:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined ? "" : currentProfile.startDay}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Ngày kết thúc:&nbsp;</div>
                                <div>
                                    {currentProfile === undefined ? "" : currentProfile.endDay}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ViewEventForm;
