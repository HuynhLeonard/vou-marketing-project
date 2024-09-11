import api from "./api";

export const callApiGetDashboardAdmin = async () => {
    const {data} = await api.get(`http://localhost:8080/api/v1/events/statistics`);
    return data;
}

export const callApiGetEventStatistic = async (idEvent) => {
    const {data} = await api.get(`http://localhost:8080/api/v1/statistics/events/${idEvent}`);
    return data;
}

