import React, {useState, useMemo, useEffect} from "react";
import { useSelector, useDispatch } from "react-redux";
import { FaSearch } from "react-icons/fa";
import { IoFilterOutline } from "react-icons/io5";
import { GoPlus } from "react-icons/go";
import { FaSackDollar } from "react-icons/fa6"
import { FaHandHoldingDollar } from "react-icons/fa6"
import { useQuery } from "react-query"
import { callApiGetAllUser } from "../service/user"
import AdminStatistic from "./AdminStatistic";


function Statistics() {
    const idUser = useSelector(state => state.auth.idUser);
  // Notification
  const [showNoti, setShowNoti] = useState(false)
  const [isError, setIsError] = useState(false);
  const [notiMsg, setNotiMsg] = useState('');
  const [shortlistUsers, setShortlistUsers] = useState([]);
  const [fulllistUsers, setFulllistUsers] = useState([]);

  const header = [
    "STT",
    "Họ tên",
    "Email",
    "Số điện thoại",
    "Quyền hạn",
    "Tình trạng",
  ]

  const closeNoti = () => {
    setShowNoti(false)
  }

  const [listNumberOfUsers, setListNumberOfUsers] = useState([])
  const [listOverview, setListOverview] = useState([0,0,0,0])

  const {isFetching, refetch} = useQuery(
    "fetch-all-users",
    () => callApiGetAllUser(idUser),
    {
      onSuccess: (data) => {
        console.log(data.metadata);
        let numberOfBrand = 0;
        let numberOfAdmin = 0;
        let numberOfPlayer = 0;
        const list = data.metadata.map((user,index) => {
          return {
            no: index+1,
            name: user.fullName,
            email: user.email,
            phone: user.phoneNumber,
            role: user.role,
            status: user.status,
          }
        })
        setFulllistUsers(data.metadata);
        setShortlistUsers(list);
      },
      onError: (error) => {
        const msgErr = error.response.data.message;
        setIsError(true);
        setShowNoti(true);
        setNotiMsg(msgErr);
      },
    }
  )

  const calculateStatistic = () => {
    let numberOfBrand = 0;
    let numberOfAdmin = 0;
    let numberOfPlayer = 0;

    let numberOfActive = 0;
    let numberOfPending = 0;
    let numberOfInactive = 0;
    
    if(fulllistUsers.length != 0){
      fulllistUsers.map((user,index) => {
        if(user.role === "BRAND"){
          numberOfBrand++;
        } else if(user.role === "ADMIN"){
          numberOfAdmin++;
        } else {
          numberOfPlayer++;
        }

        if(user.status === "PENDING"){
          numberOfPending++;
        } else if(user.status === "ACTIVE"){
          numberOfActive++;
        } else {
          numberOfInactive++;
        }
      })

      setListOverview([fulllistUsers.length,numberOfActive,numberOfPending,numberOfInactive]);
      setListNumberOfUsers([numberOfPlayer, numberOfBrand, numberOfAdmin]);
    }
  }

  useEffect(()=> {
    calculateStatistic();
  },[fulllistUsers])

  const newRows = useMemo(() => {
    const nRows = shortlistUsers.map((row,index) => {
      if (row.status.toLowerCase() === 'active') {
        return {...row, status: <div className="border-2 border-active rounded-[50px] pl-[10px] pr-[10px] pt-1 pb-1 text-active">Active</div>}
      }
      else if (row.status.toLowerCase() === 'inactive') {
        return {...row, status: <div className="border-2 border-red rounded-[50px] pl-1 pr-1 pt-1 pb-1 text-red">Inactive</div>}
      }
      else if (row.status.toLowerCase() === 'pending') {
        return {...row, status: <div className="border-2 border-pending rounded-[50px] pl-1 pr-1 pt-1 pb-1 text-pending">Pending</div>}
      }
    })
    return nRows
  }, [shortlistUsers])

  const scrollViewStyle = {
    minHeight: "550px",
    maxHeight: "550px",
  }

  const [isOpenEditAccount, setIsOpenEditAccount] = useState(false)
  const [isOpenAddAccount, setIsOpenAddAccount] = useState(false)
  const [userInfo, setUserInfo] = useState(null);
  // for updating info user
  useEffect(() => {
    refetch();
  },[isOpenAddAccount, isOpenEditAccount])


  function handleCloseForm() {
    setIsOpenEditAccount(false)
  }

  function handleCloseAddForm() {
    setIsOpenAddAccount(false)
  }
  
  function handleOpenForm() {
    setIsOpenEditAccount(true)
  }

  const showNotification = (isError,content) => {
    setIsError(isError);
    setShowNoti(true);
    setNotiMsg(content);
  }


  useEffect(() => {
    console.log(userInfo);
  }, [isOpenEditAccount])

  return(
    <AdminStatistic listNumUsers={listNumberOfUsers} />
  )
}

export default Statistics;
