import api from "./api";

export const callApiGetAllBrands = async () => {
    const {data} = await api.get(`http://localhost:8080/api/v1/brands`);
    return data;
}