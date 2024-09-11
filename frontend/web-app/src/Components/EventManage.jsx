import React, { useEffect, useState, useMemo } from "react";
import { FaHandHoldingDollar } from 'react-icons/fa6'
import { FaSackDollar } from 'react-icons/fa6'
import { Carousel } from "flowbite-react";
import { useQuery } from 'react-query';
import { useMutation } from "react-query";
import userManageImage from "../utils/images/userManage.png";
import gameManageImage from "../utils/images/gameManage.png";
import voucherManageImage from "../utils/images/voucherManage.png";
import statisticManageImage from "../utils/images/statisticManage.png";

import api from "../service/api";
import axios from "axios";

import eventDashboard from "../utils/images/eventDashboard.png";
import userDashboard from "../utils/images/userDashboard.png";
import brandDashboard from "../utils/images/brandDashboard.png";
import voucherDashboard from "../utils/images/voucherDashboard.png";

import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { convertDataToOutputString } from "../utils/date";

import { callApiGetAllUser } from '../service/user';
import { callApiGetAllBrands } from '../service/brand';
import { callApiGetItems } from '../service/item';
import { callApiGetDashboardAdmin } from '../service/statistic';
import { updateStatesEvent } from '../redux/event';
import ViewEventForm from "./ViewEventForm"
import "../Styles/Manage.css";

function EventManage() {
    const [currentEvent, setCurrentEvent] = useState({});
    const [isOpenViewEvent, setIsOpenViewEvent] = useState(false);

    const dispatch = useDispatch();
  
    const idUser = useSelector(state => state.auth.idUser);
    const [totalEvents, setTotalEvents] = useState(0);
    const [totalVouchers, setTotalVouchers] = useState(0);
    const [totalBrandAccounts, setTotalBrandAccounts] = useState(0);
    const [totalPlayerAccounts, setTotalPlayerAccounts] = useState(0);
    const [fullListEvents, setFullListEvents] = useState([])
    const [listEvents, setListEvents] = useState([]);
    const [isOpenInfoForm, setIsOpenInfoForm] = useState(false);
    const [eventInfo, setEventInfo] = useState(null);
    
    
    function handleOpenForm() {
        setIsOpenInfoForm(true)
    }



  const {isFetching: isFetchingItems, refetch: refetchItems} = useQuery(
    "fetch-items",
    () => callApiGetItems(),
    {
      onSuccess: (data) => {
        console.log(data);
        const listAvailableBrands = [];
        const listAvailableItems = data.metadata;
        dispatch(updateStatesEvent({listAvailableBrands,listAvailableItems}))        
      },
      onError: (error) => {
        // Handle error and log the appropriate message
        console.log(error.response?.data?.message || error.message);
      },      
      staleTime: Infinity, // Data will never be considered stale
      cacheTime: Infinity, // Data will be cached indefinitely   
    }
  )

  const {isFetching, refetch} = useQuery(
    "fetch-items-accounts",
    async () => {
      const accounts = await callApiGetAllUser(idUser);
      const dataStatistic = await callApiGetDashboardAdmin();
      return {accounts,dataStatistic}
    },
    {
      onSuccess: (data) => {
        console.log(data);
        const totalAccs = data.accounts?.metadata;
        // const totalAccs = temp
        let numberOfBrand = 0;
        let numberOfPlayer = 0;

        if(totalAccs.length != 0){
          totalAccs?.map((user,index) => {
            if(user.role === "BRAND"){
              numberOfBrand++;
            } else if(user.role === "PLAYER"){
              numberOfPlayer++;
            }
          })
        }
        setTotalBrandAccounts(numberOfBrand);
        setTotalPlayerAccounts(numberOfPlayer);  

        if(data.dataStatistic?.success){
          const statistic = data.dataStatistic?.metadata;
          console.log("HE: ",statistic);
          setTotalEvents(data.dataStatistic?.metadata?.totalEvents);
          setTotalVouchers(data.dataStatistic?.metadata?.totalVouchers);
          setFullListEvents(data.dataStatistic?.metadata?.eventList)
          const list = data.dataStatistic?.metadata?.eventList.map((event,index) => {
            return {
              no: index+1,
              name: event.eventName,
              email: event.numberOfVouchers || 0,
              phone: event.shareCount || 0,
              role: convertDataToOutputString(event.startDate),
              status: convertDataToOutputString(event.endDate),
            }
          })
          setListEvents(list);
        }
        
      },
      onError: (error) => {
        // Handle error and log the appropriate message
        console.log(error.response?.data?.message || error.message);
      },      
    }
  )

  useEffect(() => {
    console.log(fullListEvents);
},[fullListEvents])

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
                                </tr>
                            </thead>
                            <tbody>
                                {fullListEvents.map((obj, index) => {
                                    return (
                                        <tr
                                            class="hover cursor-pointer"
                                            onClick={() => {
                                                setCurrentEvent(fullListEvents[index]);
                                                setIsOpenViewEvent(true);
                                            }}
                                        >
                                            <td>{index + 1}</td>
                                            <td>
                                                <img class="object-fill" src={obj.imageUrl} alt="Album" />
                                            </td>
                                            <td>{(obj.eventName)}</td>
                                            <td>{obj.numberOfVouchers}</td>
                                            <td>{obj.shareCount ? obj.shareCount : 0}</td>
                                            <td>{convertDataToOutputString(obj.startDate)}</td>
                                            <td>{convertDataToOutputString(obj.endDate)}</td>
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
                                {fullListEvents.map((obj, index) => {
                                    return (
                                        <tr
                                            class="hover cursor-pointer"
                                            onClick={() => {
                                                setCurrentEvent(fullListEvents[index]);
                                                setIsOpenViewEvent(true);
                                            }}
                                        >
                                            <td>{index + 1}</td>
                                            <td>{(obj.name)}</td>
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

export default EventManage;
