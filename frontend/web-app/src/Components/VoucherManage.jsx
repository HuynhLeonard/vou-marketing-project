import React, { useEffect, useState } from "react";

import voucherData from "../utils/jsonFiles/voucherData.json";
import avatar from "../utils/images/ava.jpg";
import qr from "../utils/images/qrCode.png";
import "../Styles/Manage.css";

function VoucherManage() {
    const [voucherProfileData, setVoucherProfileData] = useState([]);
    const [currentVoucher, setCurrentVoucher] = useState({});

    const toTitleCase = (phrase) => {
        return phrase
            .toLowerCase()
            .split(" ")
            .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
            .join(" ");
    };

    const hideUserProfile = () => {
        const layerBlur = document.querySelectorAll(".layer-blur");
        const voucherProfile = document.querySelectorAll(".voucher-profile");

        layerBlur.forEach((layer) => {
            layer.classList.remove("layer-show");
            layer.classList.add("layer-hidden");
        });

        voucherProfile.forEach((layer) => {
            layer.classList.remove("layer-show");
            layer.classList.add("layer-hidden");
        });
    };

    const showVoucherProfile = (profileIndex) => {
        const layerBlur = document.querySelectorAll(".layer-blur");
        const voucherProfile = document.querySelectorAll(".voucher-profile");

        layerBlur.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        voucherProfile.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        voucherProfileData.forEach((item, index) => {
            if (index === profileIndex) {
                setCurrentVoucher(item);
            }
        });
    };

    useEffect(() => {
        var voucherList = [];
        var keys = Object.keys(voucherData);
        keys.forEach(function (key) {
            voucherList.push(voucherData[key]);
        });
        setVoucherProfileData(voucherList);
    }, [voucherProfileData, currentVoucher]);

    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <div class="flex w-full p-5 sm:p-1 sm:pt-5">
                    <div class="text-center w-1/2 mr-2">
                        <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                            DANH SÁCH VOUCHER
                        </div>
                        <div class="w-full h-[500px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            STT
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base text-center">
                                            Hình ảnh
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base w-[20%]">
                                            Tên voucher
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base w-[20%]">
                                            Code voucher
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base w-[20%]">
                                            Giá trị voucher
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base w-[10%]">
                                            Ngày hết hạn
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {voucherProfileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    showVoucherProfile(index);
                                                }}
                                            >
                                                <td>{index + 1}</td>
                                                <td>
                                                    <img
                                                        class="object-fill"
                                                        src={avatar}
                                                        alt="Album"
                                                    />
                                                </td>
                                                <td>{toTitleCase(obj.voucherName)}</td>
                                                <td>{obj.code}</td>
                                                <td>{obj.voucherPrice}</td>
                                                <td>{obj.expirationDate}</td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="text-center w-1/2">
                        <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                            DANH SÁCH EVENT
                        </div>
                        <div class="w-full h-[500px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            STT
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base text-center">
                                            Hình ảnh
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base w-[20%]">
                                            Tên voucher
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base w-[20%]">
                                            Code voucher
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base w-[20%]">
                                            Giá trị voucher
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base w-[10%]">
                                            Ngày hết hạn
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {voucherProfileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    showVoucherProfile(index);
                                                }}
                                            >
                                                <td>{index + 1}</td>
                                                <td>
                                                    <img
                                                        class="object-fill"
                                                        src={avatar}
                                                        alt="Album"
                                                    />
                                                </td>
                                                <td>{toTitleCase(obj.voucherName)}</td>
                                                <td>{obj.code}</td>
                                                <td>{obj.voucherPrice}</td>
                                                <td>{obj.expirationDate}</td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div
                        class="backdrop-blur-sm w-full h-full absolute top-0 left-0 z-20 cursor-pointer layer-blur layer-hidden"
                        onClick={hideUserProfile}
                    ></div>
                    <div class="card lg:card-side bg-base-200 shadow-2xl sm:w-[65%] xl:w-[55%] 2xl:w-[55%] absolute top-[20%] sm:left-[22%] xl:left-[24%] 2xl:left-[24%] z-20 voucher-profile layer-hidden">
                        <figure class="w-1/2">
                            <img class="object-cover" src={avatar} alt="Album" />
                        </figure>
                        <div class="card-body w-1/2">
                            <div class="flex justify-around items-center mb-5">
                                <div class="flex flex-col items-center">
                                    <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-red-500">
                                        {currentVoucher.voucherName === undefined
                                            ? ""
                                            : toTitleCase(currentVoucher.voucherName)}
                                    </div>
                                    <div class="badge badge-info">{currentVoucher.code}</div>
                                </div>
                                <figure class="w-1/4">
                                    <img class="object-cover" src={qr} alt="Album" />
                                </figure>
                            </div>
                            <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                                <div>Giá trị voucher:&nbsp;</div>
                                <div>{currentVoucher.voucherPrice}</div>
                            </div>
                            <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                                <div>Ngày hết hạn:&nbsp;</div>
                                <div>{currentVoucher.expirationDate}</div>
                            </div>
                            <div class="flex flex-col sm:text-base xl:text-lg 2xl:text-xl">
                                <div class="flex-none text-left">Mô tả voucher:&nbsp;</div>
                                <div class="p-0 h-36 w-full overflow-x-hidden">
                                    {currentVoucher.description}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="lg:hidden"></div>
        </div>
    );
}

export default VoucherManage;
