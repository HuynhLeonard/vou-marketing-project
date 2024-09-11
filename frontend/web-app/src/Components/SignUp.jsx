import React, { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEye, faEyeSlash } from "@fortawesome/free-solid-svg-icons";
import { faFacebook, faTwitter, faTelegram, faWhatsapp } from "@fortawesome/free-brands-svg-icons";
import { useMutation } from "react-query";
import { callApiSignUp } from "../service/user";
import { text } from "@fortawesome/fontawesome-svg-core";

const SignUp = () => {
    const [phoneNumber, setPhoneNumber] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    const handleSubmit = (e) => {
        console.log(account);
    };

    const [showNoti, setShowNoti] = useState(false)
    const [error, setError] = useState("");
    const [account, setAccount] = useState({
        fullName: "",
        username: "",
        password: "",
        email: "",
        phoneNumber: "",
        role: "BRAND",
    });

    const setInfo = (e) => {
        const fullName = account.username;
        setAccount((prev) => {
                return {
                    ...prev,
                    [e.target.name]: e.target.value,
                    fullName : fullName,
                }
            }
        )

    }

    const signUpMutation = useMutation(
        (account) => callApiSignUp(account),
        {
            onSuccess: (data) => {
                console.log(data);
                if(data.success){
                    window.location.href = 'http://localhost:3000';
                } else {
                    console.log("Sign up failed");
                }
            },
            onError: (error) =>{
                const msgErr = error.response.data.message;
                setError(msgErr);
                setShowNoti(true);               
            } 
        }
    )

    const submitHandler = async (e) => {
        console.log("Submit: ", account);
        if(account.username === "" || account.email === "" || account.password === "" || account.phoneNumber === ""){
            setError("Vui lòng điền đầy đủ các ô nhập liệu");
            setShowNoti(true);
            return;
        }

        const accountRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{8,}$/;

        if (!accountRegex.test(account.password)) {
            setError("Mật khẩu bao gồm 8 ký tự trở lên bao gồm chữ hoa, chữ thường, số, ký hiệu (ví dụ: !@#$)");
            setShowNoti(true);
            return;
        }
        if (account.password.length < 8) {
            setError("Mật khẩu phải lớn hơn 8 kí tự");
            setShowNoti(true);
            return;
        }

        signUpMutation.mutate(account);
    }

    const closeNoti = () => {
        setShowNoti(false)
    }

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
                    <div className="card glass shadow-2xl relative w-[400px] sm:w-[500px]">
                        <div className="card-body flex flex-col items-center">
                            <h2 className="card-title text-center text-red-500 text-[30px] mb-4">
                                Sign Up
                            </h2>
                            <div className="w-full">
                                <div className="mb-4">
                                    <label
                                        className="block mb-2 text-lg font-bold text-red-500"
                                        htmlFor="phoneNumber"
                                    >
                                        Username
                                    </label>
                                    <input
                                        type="text"
                                        name="username"
                                        onChange={setInfo}
                                        className="w-full px-3 py-2 border rounded bg-white text-black focus:outline-none focus:ring focus:ring-blue-300"
                                        required
                                    />
                                </div>
                                {/* Email Field */}
                                <div className="mb-4">
                                    <label
                                        className="block mb-2 text-lg font-bold text-red-500"
                                        htmlFor="email"
                                    >
                                        Full Name
                                    </label>
                                    <input
                                        type="text"
                                        name="fullName"
                                        onChange={setInfo}
                                        className="w-full px-3 py-2 border rounded bg-white text-black focus:outline-none focus:ring focus:ring-blue-300"
                                        required
                                    />
                                </div>
                                <div className="mb-4">
                                    <label
                                        className="block mb-2 text-lg font-bold text-red-500"
                                        htmlFor="email"
                                    >
                                        Email
                                    </label>
                                    <input
                                        type="text"
                                        name="email"
                                        onChange={setInfo}
                                        className="w-full px-3 py-2 border rounded bg-white text-black focus:outline-none focus:ring focus:ring-blue-300"
                                        required
                                    />
                                </div>
                                {/* Password Field */}
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
                                            name="password"
                                            onChange={setInfo}
                                            className="w-full px-3 py-2 border rounded bg-white text-black focus:outline-none focus:ring focus:ring-blue-300"
                                            required
                                        />
                                        <button
                                            type="button"
                                            onClick={() => setShowPassword(!showPassword)}
                                            className="absolute inset-y-0 right-3 flex items-center justify-center text-sm focus:outline-none"
                                        >
                                            <FontAwesomeIcon
                                                icon={showPassword ? faEyeSlash : faEye}
                                            />
                                        </button>
                                    </div>
                                </div>
                                {/* Confirm Password Field */}
                                <div className="mb-4 relative">
                                    <label
                                        className="block mb-2 text-lg font-bold text-red-500"
                                        htmlFor="confirmPassword"
                                    >
                                        Phone Number
                                    </label>
                                    <div className="relative">
                                        <input
                                            type="text"
                                            name="phoneNumber"
                                            onChange={setInfo}
                                            className="w-full px-3 py-2 border rounded bg-white text-black focus:outline-none focus:ring focus:ring-blue-300"
                                            required
                                        />
                                    </div>
                                </div>
                                {/* Submit Button */}
                                <div className="card-actions mb-4">
                                    <button
                                        type="submit"
                                        className="btn btn-primary w-full text-white"
                                        onClick={submitHandler}
                                    >
                                        Sign Up
                                    </button>
                                </div>
                            </div>
                            {/* Social Media Links */}
                            <div className="flex flex-col items-center mt-4">
                                <div className="flex items-center">
                                    <p className="text-sm text-black-600 mr-2">
                                        Already have an account?
                                    </p>
                                    <a href="/" className="text-blue-500 hover:underline">
                                        Login
                                    </a>
                                </div>
                                <div className="flex justify-center space-x-4 mt-2">
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

export default SignUp;
