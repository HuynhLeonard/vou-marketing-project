import api from "./api";

export const callApiGetItems = async () => {
    const {data} = await api.get(`http://localhost:8080/api/v1/items`);
    return data;
}