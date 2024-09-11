import api from "./api";

export const callApiCreateEvent = async (eventData) => {
    const {data} = await api.post(`http://localhost:8080/api/v1/events`,eventData);
    return data;
}

export const callApiUploadEventImgs = async (idEvent, imagesData) => {
    const formData = new FormData();
    formData.append('bannerFile',imagesData.bannerFile);
    formData.append('qrImage',imagesData.QRImage);
    formData.append('voucherImg',imagesData.voucherImg);

    const {data} = await api.put(`http://localhost:8080/api/v1/events?id_event=${idEvent}`,
        formData,
        {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        }
    );
    
    return data;
}

export const callApiGetMyEvents = async (brandId) => {
    console.log(brandId);
    const {data} = await api.get(`http://localhost:8080/api/v1/events?brandId=${brandId}`);
    return data;
}

export const callApiGetEventDetail = async (eventId) => {
    const {data} = await api.get(`http://localhost:8080/api/v1/events/${eventId}`);
    return data;
}

export const callApiUpdateEventDetail = async (eventId, updateData) => {
    console.log(eventId);
    console.log("heyy: ", updateData)
    const {data} = await api.put(`http://localhost:8080/api/v1/events/${eventId}`,updateData);
    return data;
}