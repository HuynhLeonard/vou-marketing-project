// src/components/SignIn.js
import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";
import { faFacebook, faTwitter, faTelegram, faWhatsapp } from "@fortawesome/free-brands-svg-icons";
import { useMutation } from "react-query";
import { callApiSignIn } from "../service/user";
import { loginSuccess } from "../redux/auth";
import Notification from "./Notification";
import { useDispatch } from "react-redux";

const SignIn = () => {
    const dispatch = useDispatch();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [rememberMe, setRememberMe] = useState(false);
    const [showNoti, setShowNoti] = useState(false);
    const [isError, setIsError] = useState(false);
    const [notiMsg, setNotiMsg] = useState("");

    const [account, setAccount] = useState({
        username: "",
        password: "",
    });

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(account);
    };

    const setInfo = (e) => {
        setAccount((prev) => {
            return {
                ...prev,
                [e.target.name]: e.target.value,
            };
        });
    };

    const loginMutation = useMutation((account) => callApiSignIn(account), {
        onSuccess: (data) => {
            console.log(data);
            if (data.success) {
                const { token, account } = data.metadata;
                // console.log(account);
                localStorage.setItem("accessToken", token);
                localStorage.setItem("idUser", account.idUser);
                localStorage.setItem("avatarUrl", account.avatarUrl);
                localStorage.setItem("username", account.username);
                localStorage.setItem("currentTab", "Trang chủ");
                // localStorage.setItem('fullName', account.fullName);
                // localStorage.setItem('email', account.email);
                // localStorage.setItem('phoneNumber', account.phoneNumber);
                // localStorage.setItem('address', account.address);
                // localStorage.setItem('lockedDate', account.lockedDate);
                // localStorage.setItem('role', account.role);
                // localStorage.setItem('status', account.status);
                // localStorage.setItem('field', account.field);
                // localStorage.setItem('longitude', account.longitude);
                // localStorage.setItem('latitude', account.latitude);

                const userInfo = {
                    accessToken: token,
                    idUser: account.idUser,
                    avatarUrl: account.avatarUrl,
                    username: account.username,
                    fullName: account.fullName,
                    email: account.email,
                    phoneNumber: account.phoneNumber,
                    address: account.address,
                    status: account.status,
                    lockedDate: account.lockedDate,
                    role: account.role,
                    expiresIn: "10h",
                    field: account.field || "",
                    longitude: account.longitude || "",
                    latitude: account.latitude || "",
                };
                dispatch(loginSuccess(userInfo));

                if (account.role === "BRAND") {
                    window.location.href = "http://localhost:3000/brand";
                } else {
                    window.location.href = "http://localhost:3000/admin";
                }
            } else {
                console.log("Login failed");
            }
        },
        onError: (error) => {
            const msgErr = error.response.data.message;
            setIsError(true);
            setShowNoti(true);
            setNotiMsg(msgErr);
        },
    });
    const submitHandler = async (e) => {
        console.log("Submit: ", account);
        if (account.username === "" || account.password === "") {
            setIsError(true);
            setShowNoti(true);
            setNotiMsg("Some fields are missing");
        }

        loginMutation.mutate(account);
    };

    return (
        <div
            className="hero min-h-screen"
            style={{
                backgroundImage:
                    "url(https://img.freepik.com/vetores-gratis/fundo-plano-geometrico-com-espaco-vazio_23-2148960852.jpg?t=st=1725800323~exp=1725803923~hmac=a1adf5c828cbf131645f1d5551dd3b2b0ba4974aa6f7d38b79721ff08cf7be10&w=1380)",
                backgroundSize: "cover",
                backgroundPosition: "center",
            }}
        >
            <div className="font-Kanit" datatheme="retro">
                <div className="hero-overlay bg-opacity-60"></div>
                <div className="hero-content text-neutral-content">
                    <div className="card glass w-[500px] shadow-2xl relative w-[400px] sm:w-[500px]">
                        <div className="card-body flex flex-col items-center">
                            <h2 className="card-title text-center text-red-500 text-[30px] mb-4">
                                Login
                            </h2>
                            <div className="w-full">
                                <div className="mb-4">
                                    <label
                                        className="block mb-2 text-lg font-bold text-red-500"
                                        htmlFor="email"
                                    >
                                        Username
                                    </label>
                                    <input
                                        type="text"
                                        id="username"
                                        name="username"
                                        onChange={setInfo}
                                        className="w-full px-3 py-2 border rounded bg-white text-black focus:outline-none focus:ring focus:ring-blue-300"
                                        required
                                    />
                                </div>
                                <div className="mb-4 relative">
                                    <label
                                        className="block mb-2 text-lg font-bold text-red-500"
                                        htmlFor="password"
                                    >
                                        Password
                                    </label>
                                    <div className="relative">
                                        <input
                                            type={showPassword ? "text" : "password"}
                                            id="password"
                                            name="password"
                                            onChange={setInfo}
                                            className="w-full px-3 py-2 border rounded bg-white text-black focus:outline-none focus:ring focus:ring-blue-300"
                                            required
                                        />
                                        {}
                                        <button
                                            type="button"
                                            onClick={() => setShowPassword(!showPassword)}
                                            className="absolute inset-y-0 right-3 flex items-center justify-center text-sm focus:outline-none"
                                        >
                                            <FontAwesomeIcon
                                                icon={showPassword ? faEye : faEyeSlash}
                                            />
                                        </button>
                                    </div>
                                    <div className="flex justify-between items-center mt-2">
                                        <div className="flex items-center">
                                            <input
                                                type="checkbox"
                                                id="rememberMe"
                                                checked={rememberMe}
                                                onChange={() => setRememberMe(!rememberMe)}
                                                className="mr-2 h-5 w-5 appearance-none border border-gray-300 rounded bg-white checked:bg-white checked:border-black checked:after:content-['✔'] checked:after:text-black checked:after:block checked:after:text-center focus:outline-none "
                                            />
                                            <label className="text-gray-400" htmlFor="rememberMe">
                                                Remember me
                                            </label>
                                        </div>
                                        <a
                                            href="/forgot-password"
                                            className="text-blue-500 hover:underline"
                                        >
                                            Forgot Password?
                                        </a>
                                    </div>
                                </div>
                                <div className="card-actions mb-4">
                                    <button
                                        type="submit"
                                        className="btn btn-primary w-full text-white"
                                        onClick={submitHandler}
                                    >
                                        Login
                                    </button>
                                </div>
                            </div>
                            <div className="flex flex-col items-center mt-4">
                                <div className="flex items-center mb-4">
                                    <p className="text-sm mr-2 text-gray-400">
                                        Don't have an account?
                                    </p>
                                    <a href="/signup" className="text-blue-500 hover:underline">
                                        Sign up
                                    </a>
                                </div>
                                {}
                                <div className="flex justify-center space-x-4 mt-4">
                                    <a
                                        href="https://facebook.com"
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        <FontAwesomeIcon
                                            icon={faFacebook}
                                            className="text-blue-600 hover:text-blue-800 text-2xl"
                                        />
                                    </a>
                                    <a
                                        href="https://twitter.com"
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        <FontAwesomeIcon
                                            icon={faTwitter}
                                            className="text-blue-400 hover:text-blue-600 text-2xl"
                                        />
                                    </a>
                                    <a
                                        href="https://telegram.org"
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        <FontAwesomeIcon
                                            icon={faTelegram}
                                            className="text-blue-500 hover:text-blue-700 text-2xl"
                                        />
                                    </a>
                                    <a
                                        href="https://zalo.me"
                                        target="_blank"
                                        rel="noopener noreferrer"
                                    >
                                        <FontAwesomeIcon
                                            icon={faWhatsapp}
                                            className="text-green-500 hover:text-green-700 text-2xl"
                                        />
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SignIn;
