import SearchBar from './BrandEvent/SearchBar'
import Card from './BrandEvent/Card'
import { useEffect, useState } from 'react';
import { logout } from '../redux/auth';
import { useDispatch, useSelector } from 'react-redux';
import TitlePage from './BrandEvent/TitlePage';
import { useQuery } from 'react-query';
import { callApiGetMyEvents } from '../service/event';
import Notification from './Notification';
import { convertDataToOutputString, compareDates } from '../utils/date';
import { updateStatesEvent } from '../redux/event';
import { callApiGetAllBrands } from '../service/brand';
import { callApiGetItems } from '../service/item';

const BrandEventManagement = () => {
  const dispatch = useDispatch();
  const [listEvents, setListEvents] = useState([])
  const brandId = useSelector(state => state.auth.idUser);


  // Notification
  const [showNoti, setShowNoti] = useState(false)
  const [isError, setIsError] = useState(false);
  const [notiMsg, setNotiMsg] = useState('');

  const closeNoti = () => {
    setShowNoti(false)
  }

  const {isFetching, refetch} = useQuery(
    "fetch-my-events",
    () => callApiGetMyEvents(brandId),
    {
      onSuccess: (data) => {
        console.log(data);
        setListEvents(data.metadata);
      },
      onError: (error) => {
        const msgErr = error.response.data.message;
        setIsError(true);
        setShowNoti(true);
        setNotiMsg(msgErr);
      },
    },
    
  )
  

  const {isFetching: isFetchingItemsAndBrands, refetch: refetchItemsAndBrands} = useQuery(
    "fetch-items-brands",
    async () => {
      const brands = await callApiGetAllBrands();
      const items = await callApiGetItems();
      return {brands,items}
    },
    {
      onSuccess: (data) => {
        console.log(data);
        const dataBrandCoop = data.brands?.metadata;
        const listAvailableBrands = dataBrandCoop.filter(brand => brand.idUser !== brandId);
        const listAvailableItems = data.items?.metadata;
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

  return (
    <div className='container w-full my-4'>
      <div className={`${showNoti ? '' : 'hidden'} flex flex-row justify-end` }>
        <Notification type={`${isError ? 'error' : 'success'}` } 
            title={`${isError ? 'Có lỗi xảy ra' : 'Thành công'}` }  content={notiMsg} close={closeNoti}/>
      </div>
      <TitlePage title={"Sự kiện của tôi"} />

      <SearchBar/>
      <div className="container flex flex-wrap gap-6 my-4">
      { listEvents.length === 0 ? (
        <div className="text-lg font-regular my-2">Bạn chưa có sự kiện nào</div>
      ) : (
        listEvents.map(event => {
          const status = compareDates(event.startDate, event.endDate);
          return <Card id={event.idEvent} key={event.idEvent}
            name={event.eventName}
            date={convertDataToOutputString(event.startDate) + " - " + convertDataToOutputString(event.endDate)}
            vouchers={event.numberOfVouchers}
            status={status} 
            bannerImg={event.imageUrl}
            />
        }) 
      )
      }
      </div>
    </div>
  )
}

export default BrandEventManagement