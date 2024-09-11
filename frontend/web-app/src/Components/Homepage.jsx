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


function Homepage() {

    // leonard here
    //const router = useRouter();
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

  const header = [
    "STT",
    "Tên sự kiện",
    "Số lượng vouchers",
    "Số lượt chia sẻ sự kiện",
    "Ngày bắt đầu",
    "Ngày kết thúc",
  ]

  const scrollViewStyle = {
    minHeight: "550px",
    maxHeight: "550px",
  }


  const newRows = useMemo(() => {
    const nRows = listEvents.map((row,index) => {
      return row;
    })
    return nRows
  }, [listEvents])

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

  },[fullListEvents])

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
                                        <div class="stat-value text-blue-600 text-8xl">{totalPlayerAccounts}</div>
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
                                        <div class="stat-value text-red-600 text-8xl">{totalBrandAccounts}</div>
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
                                        <div class="stat-value text-green-600 text-8xl">{totalEvents}</div>
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
                                        <div class="stat-value text-yellow-600 text-8xl">{totalVouchers}</div>
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
                                    <div class="stat-value text-green-600 text-8xl">{totalEvents}</div>
                                </div>
                            </Link>
                            <Link
                                to="/admin/voucherAndEvent"
                                class="bg-base-100 flex rounded-2xl shadow-2xl cursor-pointer"
                            >
                                <div class="stat">
                                    <div class="stat-title font-bold">Tổng số voucher</div>
                                    <div class="stat-value text-yellow-600 text-8xl">{totalVouchers}</div>
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

export default Homepage;
