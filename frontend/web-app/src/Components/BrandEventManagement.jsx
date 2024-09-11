import React, { useState } from "react";
import eventData from "../utils/jsonFiles/eventData.json";
import avatar from "../utils/images/ava.jpg";
import ViewEventForm from "./ViewEventForm";

function BrandEventManagement() {
    const [currentEvent, setCurrentEvent] = useState({});
    const [isOpenViewEvent, setIsOpenViewEvent] = useState(false);

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
                {isOpenViewEvent && (
                    <div>
                        <div
                            class="backdrop-blur-sm w-full h-full absolute top-0 z-20 cursor-pointer"
                            onClick={() => {
                                setIsOpenViewEvent(false);
                            }}
                        ></div>
                    </div>
                )}
                <div class="w-full p-5 text-center">
                    <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                        DANH SÁCH SỰ KIỆN
                    </div>
                    <div class="w-full h-[500px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100">
                        <table class="table">
                            <thead class="sticky top-0 bg-white z-10">
                                <tr>
                                    <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base ">
                                        STT
                                    </th>
                                    <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base text-center w-[10%]">
                                        Hình ảnh
                                    </th>
                                    <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                        Tên sự kiện
                                    </th>
                                    <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                        Số lượng voucher
                                    </th>
                                    <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                        Số lượt chia sẻ sự kiện
                                    </th>
                                    <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                        Ngày bắt đầu
                                    </th>
                                    <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                        Ngày kết thúc
                                    </th>
                                    <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base text-center">
                                        Chỉnh sửa
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                {eventData.map((obj, index) => {
                                    return (
                                        <tr
                                            class="hover cursor-pointer"
                                            onClick={() => {
                                                setCurrentEvent(eventData[index]);
                                                setIsOpenViewEvent(true);
                                            }}
                                        >
                                            <td>{index + 1}</td>
                                            <td>
                                                <img class="object-fill" src={avatar} alt="Album" />
                                            </td>
                                            <td>{toTitleCase(obj.name)}</td>
                                            <td>{obj.voucherNum}</td>
                                            <td>{obj.shareNum}</td>
                                            <td>{obj.startDay}</td>
                                            <td>{obj.endDay}</td>
                                            <td class="text-center">
                                                <button class="btn btn-sm btn-square btn-info brightness-200 m-1">
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
                                                        <line x1="3" y1="22" x2="21" y2="22"></line>
                                                    </svg>
                                                </button>
                                                <button class="btn btn-sm btn-square btn-error brightness-105 m-1">
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
                                    <td></td>
                                    <td></td>
                                    <td class="text-center">
                                        <button class="btn btn-success brightness-125 w-[65%]">
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
            </div>
            <div class="lg:hidden">
                {isOpenViewEvent && (
                    <div>
                        <div
                            class="backdrop-blur-sm w-full h-full absolute top-0 z-20 cursor-pointer"
                            onClick={() => {
                                setIsOpenViewEvent(false);
                            }}
                        ></div>
                    </div>
                )}
                <div class="flex flex-col items-center p-5">
                    <div class="font-bold text-lg sm:text-xl text-info mb-5">DANH SÁCH SỰ KIỆN</div>
                    <div class="w-full h-[500px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100 mb-5">
                        <table class="table">
                            <thead class="sticky top-0 bg-white z-10">
                                <tr>
                                    <th class="font-bold text-red-500 text-base">STT</th>
                                    <th class="font-bold text-red-500 text-base">Tên sự kiện</th>
                                    <th class="font-bold text-red-500 text-base">
                                        Số lượng voucher
                                    </th>
                                    <th class="font-bold text-red-500 text-base text-center">
                                        Chỉnh sửa
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                {eventData.map((obj, index) => {
                                    return (
                                        <tr
                                            class="hover cursor-pointer"
                                            onClick={() => {
                                                setCurrentEvent(eventData[index]);
                                                setIsOpenViewEvent(true);
                                            }}
                                        >
                                            <td>{index + 1}</td>
                                            <td>{toTitleCase(obj.name)}</td>
                                            <td>{obj.voucherNum}</td>
                                            <td class="text-center">
                                                <button class="btn btn-xs btn-square btn-info brightness-200 m-1">
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
                                                        <line x1="3" y1="22" x2="21" y2="22"></line>
                                                    </svg>
                                                </button>
                                                <button class="btn btn-xs btn-square btn-error brightness-105 m-1">
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
                                        <button class="btn btn-success brightness-125 w-[65%]">
                                            <svg
                                                xmlns="http://www.w3.org/2000/svg"
                                                class="w-8 h-8"
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
            </div>
            {isOpenViewEvent && <ViewEventForm currentProfile={currentEvent} />}
        </div>
    );
}

export default BrandEventManagement;
