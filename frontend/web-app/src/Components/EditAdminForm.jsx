/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useState, useRef } from "react";
import { MdOutlineArrowDropDown } from "react-icons/md";
import { useSelector, useDispatch } from "react-redux";
import { useMutation } from "react-query";
import avatar from "../utils/images/ava.jpg";
import { callApiUpdateAccountImage, callApiUpdateAccount } from "../service/user";
import { updateStates } from "../redux/auth";
import ChangePassForm from "./ChangePassForm";
import Notification from "./Notification";
import avatar1 from "../utils/images/avatar.png";

function EditAdminForm() {
    const data = useSelector((state) => state.auth);
    const dispatch = useDispatch();

    console.log("Redux:", data);
    const listStates = ["Active", "InActive", "Pending"];
    const [accountState, setAccountState] = useState(data.status);
    const [openStates, setOpenStates] = useState(false);

    const [avatar, setAvatar] = useState(data.avatarUrl || "/images/defaultAva.jpg");
    const [tempAvatar, setTempAvatar] = useState(avatar1);

    const hiddenFileInput = useRef(null);
    const formAccount = useRef(null);

    // Change password form
    const [showChangePassForm, setShowChangePassForm] = useState(false);
    function handleCloseForm() {
        setShowChangePassForm(false);
    }

    // Notification
    const [showNoti, setShowNoti] = useState(false);
    const [isError, setIsError] = useState(false);
    const [notiMsg, setNotiMsg] = useState("");

    const [userInfo, setUserInfo] = useState({
        idUser: data.idUser,
        username: data.username,
        fullName: data.fullName,
        phoneNumber: data.phoneNumber,
        email: data.email,
        address: data.address,
        status: data.status,
        role: data.role,
        avatarUrl: avatar,
    });

    const setValue = (e) => {
        setUserInfo({
            ...userInfo,
            [e.target.name]: e.target.value,
        });
    };

    // Change the dropdown state
    const changeState = (state) => {
        setOpenStates(!state);
    };
    const closeNoti = () => {
        setShowNoti(false);
    };

    const handleImageEdit = () => {
        hiddenFileInput.current.click();
    };

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        const imgname = e.target.files[0]?.name;
        if (imgname) {
            const fileUrl = URL.createObjectURL(file);
            setAvatar(file);
            setTempAvatar(fileUrl);
        }
    };

    const handleChangeStatus = (e) => {
        setAccountState(e.target.textContent);
        setOpenStates(false);
        setUserInfo({
            ...userInfo,
            status: e.target.textContent,
        });
    };

    const updateAccountMutation = useMutation(
        async ({ updatedData, avatar }) => {
            const updatedAccount = await callApiUpdateAccount(userInfo.idUser, updatedData);
            let updatedAva = null;
            if (avatar instanceof File) {
                updatedAva = await callApiUpdateAccountImage(userInfo.idUser, avatar);
            }
            return { updatedAccount, updatedAva };
        },
        {
            onSuccess: (data) => {
                console.log(data);
                setIsError(false);
                setShowNoti(true);
                setNotiMsg("Cập nhật thông tin thành công");

                // Update redux
                // dispatch(updateStates(data.updatedAccount.metadata));
                const avatar = data.updatedAva?.metadata;
                if (avatar !== null) {
                    const updatedData = {
                        ...data.updatedAccount.metadata,
                        avatarUrl: avatar,
                    };
                    dispatch(updateStates(updatedData));
                } else {
                    dispatch(updateStates(data.updatedAccount.metadata));
                }
            },
            onError: (error) => {
                const msgErr = error.response.data.message;
                setIsError(true);
                setShowNoti(true);
                setNotiMsg(msgErr);
            },
        }
    );

    const submitFormData = () => {
        const formData = new FormData(formAccount.current);
        const formProps = Object.fromEntries(formData);

        updateAccountMutation.mutate({ updatedData: formProps, avatar: avatar });
    };

    return (
        <div className="container w-full p-5 bg-white font-Kanit" data-theme="retro">
            {showChangePassForm && <ChangePassForm handleClose={handleCloseForm} />}
            <div className={`${showNoti ? "" : "hidden"} flex flex-row justify-end`}>
                <Notification
                    type={`${isError ? "Error" : "success"}`}
                    title={`${isError ? "Có lỗi xảy ra" : "Thành công"}`}
                    content={notiMsg}
                    close={closeNoti}
                />
            </div>
            <h1
                title={"Thông tin tài khoản"}
                class="text-center font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5"
            >
                THÔNG TIN TÀI KHOẢN
            </h1>
            <div className="container flex items-center justify-center bg-base-100 shadow-md rounded-3xl py-5 px-5 my-4 gap-5 border border-gray-200">
                <div className="flex flex-col min-w-[150px] items-center gap-4">
                    <img
                        className="inline-block h-[130px] w-[130px] rounded-full ring-2 ring-white"
                        width={130}
                        height={130}
                        src={tempAvatar}
                        alt="upload image"
                    />
                    <input
                        id="image-upload-input"
                        type="file"
                        onChange={handleImageChange}
                        ref={hiddenFileInput}
                        style={{ display: "none" }}
                    />
                    <span className="text-heading3_bold text-center badge badge-info">Admin</span>
                    <span
                        className="font-semibold cursor-pointer btn btn-success brightness-125 text-gray-300"
                        onClick={handleImageEdit}
                    >
                        Chỉnh sửa
                    </span>
                    <div
                        onClick={() => setShowChangePassForm(true)}
                        className="flex flex-col w-full"
                    >
                        <h5 className="text-base text-error text-center font-semibold underline cursor-pointer">
                            Đổi mật khẩu
                        </h5>
                    </div>
                </div>
                <form
                    id="form-account"
                    ref={formAccount}
                    className="container"
                    onSubmit={(e) => {
                        e.preventDefault();
                    }}
                >
                    <div className="flex gap-4">
                        <div className="flex flex-col px-2 py-2 grow">
                            <h5 className="text-base font-semibold">Role</h5>
                            <input
                                disabled
                                type="text"
                                className="input_text_disabled input"
                                value={userInfo.role}
                                name="role"
                            />
                        </div>
                        <div className="flex flex-col px-2 py-1 w-[424px] justify-center">
                            <h5 className="text-base font-semibold">Trạng thái</h5>
                            <div
                                className="input_dropdown input input-bordered input-info"
                                onClick={() => changeState(openStates)}
                            >
                                <div class="flex items-center mt-2">
                                    <span className="text-gray-900">{accountState}</span>
                                    <MdOutlineArrowDropDown size={28} />
                                </div>
                            </div>

                            {openStates ? (
                                <div
                                    className="absolute z-10 mt-[88px] w-[400px] origin-top-right rounded-lg bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none"
                                    role="menu"
                                    aria-orientation="vertical"
                                    aria-labelledby="menu-button"
                                >
                                    <div className="py-1 " role="none">
                                        {listStates.map((item) => (
                                            <a
                                                key={item}
                                                className="block px-4 py-2 text-sm text-gray-900 hover:bg-gray-200"
                                                role="menuitem"
                                                value={item}
                                                onClick={(e) => handleChangeStatus(e)}
                                            >
                                                {item}
                                            </a>
                                        ))}
                                    </div>
                                </div>
                            ) : (
                                <></>
                            )}
                        </div>
                    </div>
                    <div className="flex gap-4">
                        <div className="flex flex-col px-2 py-2 grow">
                            <h5 className="text-base font-semibold">Email</h5>
                            <input
                                disabled
                                type="text"
                                className="input_text_disabled input"
                                value={userInfo.email}
                                name="email"
                            />
                        </div>
                        <div className="flex flex-col px-2 py-2 min-w-[424px]">
                            <h5 className="text-base font-semibold">Điện thoại</h5>
                            <input
                                type="text"
                                className="input_text input input-bordered input-info"
                                placeholder="+84"
                                value={userInfo.phoneNumber}
                                name="phoneNumber"
                                required
                                onChange={(e) => setValue(e)}
                            />
                        </div>
                    </div>
                    <div className="flex gap-4">
                        <div className="flex flex-col px-2 py-2 grow">
                            <h5 className="text-base font-semibold">Tên đăng nhập</h5>
                            <input
                                type="text"
                                className="input_text input input-bordered input-info"
                                placeholder="username"
                                value={userInfo.username}
                                name="username"
                                required
                                onChange={(e) => setValue(e)}
                            />
                        </div>

                        <div className="flex flex-col px-2 py-2 min-w-[424px]">
                            <h5 className="text-base font-semibold">Họ và tên</h5>
                            <input
                                type="text"
                                className="input_text input input-bordered input-info"
                                placeholder="Nguyen Van A"
                                value={userInfo.fullName}
                                name="fullName"
                                required
                                onChange={(e) => setValue(e)}
                            />
                        </div>
                    </div>
                    <div className="flex flex-col px-2 py-2">
                        <h5 className="text-base font-semibold">Địa chỉ</h5>
                        <input
                            type="text"
                            className="input_text input input-bordered input-info"
                            placeholder="227 Nguyễn Văn Cừ"
                            value={userInfo.address}
                            name="address"
                            required
                            onChange={(e) => setValue(e)}
                        />
                    </div>
                    <div
                        className="primary_btn mt-8 ml-[500px] btn btn-info "
                        onClick={submitFormData}
                    >
                        Lưu thông tin
                    </div>
                </form>
            </div>
        </div>
    );
}

export default EditAdminForm;
