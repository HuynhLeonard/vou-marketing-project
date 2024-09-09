/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useEffect, useState } from "react";
import userData from "../utils/jsonFiles/userData.json";
import avatar from "../utils/images/ava.jpg";
import "../Styles/Manage.css";

function UserManage() {
    const [allProfileData, setAllProfileData] = useState([]);
    const [profileData, setProfileData] = useState([]);
    const [newProfileData, setNewProfileData] = useState([]);
    const [currentProfile, setCurrentProfile] = useState({});

    const toTitleCase = (phrase) => {
        return phrase
            .toLowerCase()
            .split(" ")
            .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
            .join(" ");
    };

    const showUserProfile = (profileIndex) => {
        const layerBlur = document.querySelectorAll(".layer-blur");
        const userProfile = document.querySelectorAll(".user-profile");

        layerBlur.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        userProfile.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        profileData.forEach((item, index) => {
            if (index === profileIndex) {
                setCurrentProfile(item);
            }
        });
    };

    const showNewUserProfile = (profileIndex) => {
        const layerBlur = document.querySelectorAll(".layer-blur");
        const newUserProfile = document.querySelectorAll(".new-user-profile");

        layerBlur.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        newUserProfile.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        newProfileData.forEach((item, index) => {
            if (index === profileIndex) {
                setCurrentProfile(item);
            }
        });
    };

    const hideUserProfile = () => {
        const layerBlur = document.querySelectorAll(".layer-blur");
        const userProfile = document.querySelectorAll(".user-profile");
        const newUserProfile = document.querySelectorAll(".new-user-profile");
        const removeProfile = document.querySelectorAll(".remove-profile");
        const removeNewProfile = document.querySelectorAll(".remove-new-profile");
        const editProfile = document.querySelectorAll(".edit-profile");

        layerBlur.forEach((layer) => {
            layer.classList.remove("layer-show");
            layer.classList.add("layer-hidden");
        });

        userProfile.forEach((layer) => {
            layer.classList.remove("layer-show");
            layer.classList.add("layer-hidden");
        });

        newUserProfile.forEach((layer) => {
            layer.classList.remove("layer-show");
            layer.classList.add("layer-hidden");
        });

        removeProfile.forEach((layer) => {
            layer.classList.remove("layer-show");
            layer.classList.add("layer-hidden");
        });

        removeNewProfile.forEach((layer) => {
            layer.classList.remove("layer-show");
            layer.classList.add("layer-hidden");
        });

        editProfile.forEach((layer) => {
            layer.classList.remove("layer-show");
            layer.classList.add("layer-hidden");
        });

        var allInputList = [
            ".username-input",
            ".name-input",
            ".email-input",
            ".phone-input",
            ".facebook-input",
            ".birth-input",
            ".small-username-input",
            ".small-name-input",
            ".small-email-input",
            ".small-phone-input",
            ".small-facebook-input",
            ".small-birth-input",
        ];

        allInputList.forEach((input) => {
            var inputValue = document.querySelector(input);
            inputValue.value = "";
        });

        var genderInput = document.querySelector(".gender-input");
        genderInput.innerHTML = currentProfile.gender === "male" ? "Nam" : "Nữ";
        var statusInput = document.querySelector(".status-input");
        statusInput.innerHTML = currentProfile.status === "Active" ? "Hoạt động" : "Bị khóa";
        var roleInput = document.querySelector(".role-input");
        roleInput.innerHTML = toTitleCase(currentProfile.role);

        var smallGenderInput = document.querySelector(".small-gender-input");
        smallGenderInput.innerHTML = currentProfile.gender === "male" ? "Nam" : "Nữ";
        var smallStatusInput = document.querySelector(".small-status-input");
        smallStatusInput.innerHTML = currentProfile.status === "Active" ? "Hoạt động" : "Bị khóa";
        var smallRoleInput = document.querySelector(".small-role-input");
        smallRoleInput.innerHTML = toTitleCase(currentProfile.role);
    };

    const removeUserProfile = (profileIndex, status) => {
        const layerBlur = document.querySelectorAll(".layer-blur");
        const removeProfile = document.querySelectorAll(".remove-profile");

        layerBlur.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        removeProfile.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        var count = -1;

        allProfileData.forEach((item) => {
            if (item.status === status.toString()) {
                if (++count === profileIndex) {
                    setCurrentProfile(item);
                }
            }
        });
    };

    const removeNewUserProfile = (profileIndex, status) => {
        const layerBlur = document.querySelectorAll(".layer-blur");
        const removeNewProfile = document.querySelectorAll(".remove-new-profile");

        layerBlur.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        removeNewProfile.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        var count = -1;

        allProfileData.forEach((item) => {
            if (item.status === status.toString()) {
                if (++count === profileIndex) {
                    setCurrentProfile(item);
                }
            }
        });
    };

    const confirmRemove = () => {
        var allUserList = [];

        allProfileData.forEach((item) => {
            if (item.accountId !== currentProfile.accountId) {
                allUserList.push(item);
            }
        });

        setAllProfileData(allUserList);
        hideUserProfile();
    };

    const editUserProfile = (profileIndex) => {
        const layerBlur = document.querySelectorAll(".layer-blur");
        const editProfile = document.querySelectorAll(".edit-profile");

        layerBlur.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        editProfile.forEach((layer) => {
            layer.classList.remove("layer-hidden");
            layer.classList.add("layer-show");
        });

        profileData.forEach((item, index) => {
            if (index === profileIndex) {
                setCurrentProfile(item);
            }
        });

        const titleList = [
            ".username-title",
            ".name-title",
            ".email-title",
            ".phone-title",
            ".gender-title",
            ".facebook-title",
            ".birth-title",
            ".small-username-title",
            ".small-name-title",
            ".small-email-title",
            ".small-phone-title",
            ".small-gender-title",
            ".small-facebook-title",
            ".small-birth-title",
        ];

        titleList.forEach((title) => {
            var titleName = document.querySelector(title);
            titleName.style.color = "black";
        });
    };

    const inputFill = (titleName, titleInput) => {
        if (titleName !== "") {
            var titleValue = document.querySelector(titleName);
            var inputValue = document.querySelector(titleInput);

            if (inputValue.value !== "") {
                if (inputValue.placeholder !== inputValue.value) {
                    titleValue.style.color = "rgb(242, 82, 82)";
                } else {
                    titleValue.style.color = "black";
                }
            } else {
                titleValue.style.color = "black";
            }
        }
    };

    const confirmSave = (buttonSize) => {
        var valueList = [];

        if (buttonSize === "large") {
            const inputList = [
                ".username-input",
                ".role-input",
                ".name-input",
                ".email-input",
                ".phone-input",
                ".gender-input",
                ".status-input",
                ".facebook-input",
                ".birth-input",
                ".ava-input",
            ];

            inputList.forEach((input) => {
                var inputValue = document.querySelector(input);
                if (inputValue.value !== "") {
                    valueList.push(inputValue.value);
                } else {
                    valueList.push(inputValue.placeholder);
                }
            });
        } else {
            const inputList = [
                ".small-username-input",
                ".small-role-input",
                ".small-name-input",
                ".small-email-input",
                ".small-phone-input",
                ".small-gender-input",
                ".small-status-input",
                ".small-facebook-input",
                ".small-birth-input",
                ".small-ava-input",
            ];

            inputList.forEach((input) => {
                var inputValue = document.querySelector(input);
                if (inputValue.value !== "") {
                    valueList.push(inputValue.value);
                } else {
                    valueList.push(inputValue.placeholder);
                }
            });
        }

        const titleList = [
            ".username-title",
            ".role-title",
            ".name-title",
            ".email-title",
            ".phone-title",
            ".gender-title",
            ".facebook-title",
            ".birth-title",
            ".small-username-title",
            ".small-role-title",
            ".small-name-title",
            ".small-email-title",
            ".small-phone-title",
            ".small-gender-title",
            ".small-facebook-title",
            ".small-birth-title",
        ];

        titleList.forEach((title) => {
            var titleName = document.querySelector(title);
            titleName.style.color = "black";
        });
    };

    function loadXHR(url) {
        return new Promise(function (resolve, reject) {
            try {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", url);
                xhr.responseType = "blob";
                xhr.onerror = function () {
                    reject("Network error.");
                };
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        resolve(xhr.response);
                    } else {
                        reject("Loading error:" + xhr.statusText);
                    }
                };
                xhr.send();
            } catch (err) {
                reject(err.message);
            }
        });
    }

    useEffect(() => {
        var allUserList = [];
        var userList = [];
        var newUserList = [];

        var keys = Object.keys(userData);
        keys.forEach(function (key) {
            if (userData[key].status !== "Pending") {
                userList.push(userData[key]);
            } else {
                newUserList.push(userData[key]);
            }
            allUserList.push(userData[key]);
        });

        setProfileData(userList);
        setNewProfileData(newUserList);
        setAllProfileData(allUserList);

        const inputList = [
            ".username-input",
            ".name-input",
            ".email-input",
            ".phone-input",
            ".facebook-input",
            ".birth-input",
            ".ava-input",
        ];

        var count = 0;

        inputList.forEach((input) => {
            var inputValue = document.querySelector(input);
            if (inputValue.value !== "") {
                if (inputValue.placeholder !== inputValue.value) {
                    count += 1;
                }
                if (input === ".ava-input") {
                    if (inputValue.value !== currentProfile.avatar) {
                        var fReader = new FileReader();
                        loadXHR("https://cdn-icons-png.flaticon.com/512/4383/4383956.png").then(
                            function (blob) {
                                fReader.readAsDataURL(blob);
                                fReader.onloadend = function (event) {
                                    if (
                                        document.querySelector(".ava-image").src !==
                                        event.target.result
                                    ) {
                                        count += 1;
                                    }
                                };
                            }
                        );
                    }
                }
            }
        });

        var genderInput = document.querySelector(".gender-input");

        if (genderInput.innerHTML !== (currentProfile.gender === "male" ? "Nam" : "Nữ")) {
            document.querySelector(".gender-title").style.color = "rgb(242, 82, 82)";
            count += 1;
        } else {
            document.querySelector(".gender-title").style.color = "black";
        }

        var statusInput = document.querySelector(".status-input");

        if (
            statusInput.innerHTML !== (currentProfile.status === "Active" ? "Hoạt động" : "Bị khóa")
        ) {
            document.querySelector(".status-title").style.color = "rgb(242, 82, 82)";
            count += 1;
        } else {
            document.querySelector(".status-title").style.color = "black";
        }

        var roleInput = document.querySelector(".role-input");

        if (roleInput.innerHTML.toLowerCase() !== currentProfile.role) {
            document.querySelector(".role-title").style.color = "rgb(242, 82, 82)";
            count += 1;
        } else {
            document.querySelector(".role-title").style.color = "black";
        }

        var saveButton = document.querySelector(".save-button");

        if (count > 0) {
            saveButton.classList.remove("btn-disabled");
        } else {
            saveButton.classList.add("btn-disabled");
        }

        const smallInputList = [
            ".small-username-input",
            ".small-name-input",
            ".small-email-input",
            ".small-phone-input",
            ".small-facebook-input",
            ".small-birth-input",
            ".small-ava-input",
        ];

        var smallCount = 0;

        smallInputList.forEach((input) => {
            var inputValue = document.querySelector(input);
            if (inputValue.value !== "") {
                if (inputValue.placeholder !== inputValue.value) {
                    smallCount += 1;
                }
                if (input === ".small-ava-input") {
                    if (inputValue.value !== currentProfile.avatar) {
                        var fReader = new FileReader();
                        loadXHR("https://cdn-icons-png.flaticon.com/512/4383/4383956.png").then(
                            function (blob) {
                                fReader.readAsDataURL(blob);
                                fReader.onloadend = function (event) {
                                    if (
                                        document.querySelector(".small-ava-image").src !==
                                        event.target.result
                                    ) {
                                        count += 1;
                                    }
                                };
                            }
                        );
                    }
                }
            }
        });

        var smallGenderInput = document.querySelector(".small-gender-input");

        if (smallGenderInput.innerHTML !== (currentProfile.gender === "male" ? "Nam" : "Nữ")) {
            document.querySelector(".small-gender-title").style.color = "rgb(242, 82, 82)";
            smallCount += 1;
        } else {
            document.querySelector(".small-gender-title").style.color = "black";
        }

        var smallStatusInput = document.querySelector(".small-status-input");

        if (
            smallStatusInput.innerHTML !==
            (currentProfile.gender === "Active" ? "Hoạt động" : "Bị khóa")
        ) {
            document.querySelector(".small-status-title").style.color = "rgb(242, 82, 82)";
            smallCount += 1;
        } else {
            document.querySelector(".small-status-title").style.color = "black";
        }

        var smallRoleInput = document.querySelector(".small-role-input");

        if (smallRoleInput.innerHTML.toLowerCase() !== currentProfile.role) {
            document.querySelector(".small-role-title").style.color = "rgb(242, 82, 82)";
            smallCount += 1;
        } else {
            document.querySelector(".small-role-title").style.color = "black";
        }

        var smallSaveButton = document.querySelector(".small-save-button");

        if (smallCount > 0) {
            smallSaveButton.classList.remove("btn-disabled");
        } else {
            smallSaveButton.classList.add("btn-disabled");
        }

        if (document.querySelector(".ava-input").value !== "") {
            var input = document.querySelector(".ava-input");
            var fReader = new FileReader();
            fReader.readAsDataURL(input.files[0]);
            fReader.onloadend = function (event) {
                document.querySelector(".ava-image").src = event.target.result;
            };
        }

        if (document.querySelector(".small-ava-input").value !== "") {
            var smallInput = document.querySelector(".small-ava-input");
            var smallfReader = new FileReader();
            smallfReader.readAsDataURL(smallInput.files[0]);
            smallfReader.onloadend = function (event) {
                document.querySelector(".small-ava-image").src = event.target.result;
            };
        }

        return () => {};
    }, [profileData, newProfileData, allProfileData, currentProfile]);

    return (
        <div class="bg-white font-Kanit" data-theme="retro">
            <div class="lg:block hidden">
                <div class="flex w-full p-5 sm:p-1 sm:pt-5">
                    <div class="flex flex-col mr-2 items-center w-2/3 sm:mr-1">
                        <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                            DANH SÁCH NGƯỜI DÙNG
                        </div>
                        <div class="w-full h-[520px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            STT
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Tên tài khoản
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Email
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Vai trò
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Trạng thái
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base text-center">
                                            Chỉnh sửa
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {profileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    showUserProfile(index);
                                                }}
                                            >
                                                <td>{index + 1}</td>
                                                <td>{toTitleCase(obj.fullName)}</td>
                                                <td>{obj.email}</td>
                                                <td>{toTitleCase(obj.role)}</td>
                                                <td>
                                                    {obj.status === "Active"
                                                        ? "Hoạt động"
                                                        : "Bị khóa"}
                                                </td>
                                                <td class="text-center">
                                                    <button
                                                        class="btn btn-sm btn-square btn-info brightness-200 m-1"
                                                        onClick={(e) => {
                                                            e.stopPropagation();
                                                            editUserProfile(index);
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-5 w-5"
                                                            viewBox="0 0 24 24"
                                                            fill="none"
                                                            stroke="#000000"
                                                            stroke-width="2"
                                                            stroke-linecap="round"
                                                            stroke-linejoin="round"
                                                        >
                                                            <polygon points="14 2 18 6 7 17 3 17 3 13 14 2"></polygon>
                                                            <line
                                                                x1="3"
                                                                y1="22"
                                                                x2="21"
                                                                y2="22"
                                                            ></line>
                                                        </svg>
                                                    </button>
                                                    <button
                                                        class="btn btn-sm btn-square btn-error brightness-105 m-1"
                                                        onClick={(e) => {
                                                            e.stopPropagation();
                                                            removeUserProfile(index, true);
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-5 w-5"
                                                            fill="none"
                                                            viewBox="0 0 24 24"
                                                            stroke="currentColor"
                                                        >
                                                            <path
                                                                stroke-linecap="round"
                                                                stroke-linejoin="round"
                                                                stroke-width="2"
                                                                d="M6 18L18 6M6 6l12 12"
                                                            />
                                                        </svg>
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="flex flex-col ml-2 grow items-center sm:ml-1">
                        <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-info mb-5">
                            DANH SÁCH HỒ SƠ CHỜ
                        </div>
                        <div class="w-full h-[520px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            STT
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Tên tài khoản
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base">
                                            Vai trò
                                        </th>
                                        <th class="font-bold text-red-500 sm:text-sm xl:text-base 2xl:text-base text-center">
                                            Xác nhận
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {newProfileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    showNewUserProfile(index);
                                                }}
                                            >
                                                <td>{index + 1}</td>
                                                <td>{obj.userName}</td>
                                                <td>{toTitleCase(obj.role)}</td>
                                                <td class="text-center">
                                                    <button class="btn btn-sm btn-square btn-success brightness-125 m-1">
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-5 w-5"
                                                            viewBox="0 0 24 24"
                                                            fill="none"
                                                            stroke="#000000"
                                                            stroke-width="2"
                                                            stroke-linecap="round"
                                                            stroke-linejoin="round"
                                                        >
                                                            <polyline points="20 6 9 17 4 12"></polyline>
                                                        </svg>
                                                    </button>
                                                    <button
                                                        class="btn btn-sm btn-square btn-error brightness-105 m-1"
                                                        onClick={(e) => {
                                                            e.stopPropagation();
                                                            removeNewUserProfile(index, false);
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-5 w-5"
                                                            fill="none"
                                                            viewBox="0 0 24 24"
                                                            stroke="currentColor"
                                                        >
                                                            <path
                                                                stroke-linecap="round"
                                                                stroke-linejoin="round"
                                                                stroke-width="2"
                                                                d="M6 18L18 6M6 6l12 12"
                                                            />
                                                        </svg>
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div
                    class="backdrop-blur-sm w-full h-full absolute top-0 z-20 cursor-pointer layer-blur layer-hidden"
                    onClick={hideUserProfile}
                ></div>
                <div class="card lg:card-side bg-base-200 shadow-2xl sm:w-[65%] xl:w-[55%] 2xl:w-[55%] absolute top-[20%] sm:left-[22%] xl:left-[24%] 2xl:left-[24%] z-20 user-profile layer-hidden">
                    <figure class="w-1/2">
                        <img class="object-cover" src={avatar} alt="Album" />
                    </figure>
                    <div class="card-body">
                        <div class="flex flex-col items-center mb-5">
                            <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-red-500">
                                {currentProfile.userName}
                            </div>
                            <div class="badge badge-info">
                                {currentProfile.role === undefined
                                    ? ""
                                    : toTitleCase(currentProfile.role)}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Họ và tên:&nbsp;</div>
                            <div>
                                {currentProfile.fullName === undefined
                                    ? ""
                                    : toTitleCase(currentProfile.fullName)}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Email:&nbsp;</div>
                            <div>{currentProfile.email}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Điện thoại:&nbsp;</div>
                            <div>{currentProfile.phoneNumber}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Giới tính:&nbsp;</div>
                            <div>{currentProfile.gender === "male" ? "Nam" : "Nữ"}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Facebook:&nbsp;</div>
                            <div>{currentProfile.accountFacebook}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Ngày sinh:&nbsp;</div>
                            <div>{currentProfile.dayOfBirth}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Trạng thái:&nbsp;</div>
                            <div>
                                {currentProfile.status === "Active" ? "Hoạt động" : "Bị khóa"}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card lg:card-side bg-base-200 shadow-2xl sm:w-[65%] xl:w-[55%] 2xl:w-[55%] absolute top-[20%] sm:left-[22%] xl:left-[24%] 2xl:left-[24%] z-20 new-user-profile layer-hidden">
                    <figure class="w-1/2">
                        <img class="object-cover" src={avatar} alt="Album" />
                    </figure>
                    <div class="card-body">
                        <div class="flex flex-col items-center mb-5">
                            <div class="font-bold sm:text-lg xl:text-xl 2xl:text-2xl text-red-500">
                                {currentProfile.userName}
                            </div>
                            <div class="badge badge-info">
                                {currentProfile.role === undefined
                                    ? ""
                                    : toTitleCase(currentProfile.role)}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Họ và tên:&nbsp;</div>
                            <div>
                                {currentProfile.fullName === undefined
                                    ? ""
                                    : toTitleCase(currentProfile.fullName)}
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Email:&nbsp;</div>
                            <div>{currentProfile.email}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Điện thoại:&nbsp;</div>
                            <div>{currentProfile.phoneNumber}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Giới tính:&nbsp;</div>
                            <div>{currentProfile.gender === "male" ? "Nam" : "Nữ"}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Facebook:&nbsp;</div>
                            <div>{currentProfile.accountFacebook}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Ngày sinh:&nbsp;</div>
                            <div>{currentProfile.dayOfBirth}</div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div>Trạng thái:&nbsp;</div>
                            <div>
                                {currentProfile.status === "Active" ? "Hoạt động" : "Bị khóa"}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card bg-base-200 shadow-2xl w-[25%] absolute top-[30%] left-[40%] z-20 remove-profile layer-hidden">
                    <div class="card-body">
                        <div class="font-bold text-center md:text-lg xl:text-xl 2xl:text-2xl text-red-500">
                            Xóa người dùng ?
                        </div>
                        <p class="md:text-base xl:text-lg 2xl:text-xl mb-5">
                            Thao tác này sẽ vĩnh viễn xóa thông tin của người dùng.
                        </p>
                        <div class="card-actions justify-center">
                            <button
                                class="btn btn-error brightness-105 text-white"
                                onClick={() => {
                                    confirmRemove();
                                    hideUserProfile();
                                }}
                            >
                                Xóa ngay
                            </button>
                        </div>
                    </div>
                </div>
                <div class="card bg-base-200 shadow-2xl w-[25%] absolute top-[30%] left-[40%] z-20 remove-new-profile layer-hidden">
                    <div class="card-body">
                        <div class="font-bold text-center md:text-lg xl:text-xl 2xl:text-2xl text-red-500">
                            Xóa hồ sơ ?
                        </div>
                        <p class="md:text-base xl:text-lg 2xl:text-xl mb-5">
                            Thao tác này sẽ vĩnh viễn xóa hồ sơ của người dùng.
                        </p>
                        <div class="card-actions justify-center">
                            <button
                                class="btn btn-error brightness-105 text-white"
                                onClick={() => {
                                    confirmRemove();
                                    hideUserProfile();
                                }}
                            >
                                Xóa ngay
                            </button>
                        </div>
                    </div>
                </div>
                <div class="card lg:card-side bg-base-200 shadow-2xl sm:w-[65%] xl:w-[55%] 2xl:w-[55%] absolute top-[20%] sm:left-[22%] xl:left-[24%] 2xl:left-[24%] z-20 edit-profile layer-hidden">
                    <button
                        className="save-button btn btn-circle btn-success btn-disabled brightness-125 absolute sm:top-[86%] sm:left-[93%] xl:top-[88%%] xl:left-[93%] 2xl:top-[86%] 2xl:left-[93%]"
                        onClick={() => {
                            confirmSave("large");
                        }}
                    >
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            class="h-6 w-6"
                            viewBox="0 0 24 24"
                            fill="none"
                            stroke="#ffffff"
                            stroke-width="2"
                            stroke-linecap="round"
                            stroke-linejoin="round"
                        >
                            <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
                            <polyline points="17 21 17 13 7 13 7 21"></polyline>
                            <polyline points="7 3 7 8 15 8"></polyline>
                        </svg>
                    </button>
                    <figure class="w-1/2">
                        <img
                            class="object-cover ava-image cursor-pointer"
                            alt="Album"
                            src={avatar}
                            onClick={() => {
                                document.querySelector(".ava-input").click();
                            }}
                        />
                        <input type="file" class="ava-input hidden" accept=".jpg,.jpeg,.png" />
                    </figure>
                    <div class="card-body">
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="username-title flex-none">Tên tài khoản:&nbsp;</div>
                            <input
                                type="text"
                                placeholder={currentProfile.userName}
                                class="username-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".username-title", ".username-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="role-title flex-none">Vai trò:&nbsp;</div>
                            <div class="dropdown p-0 h-7 w-full">
                                <div
                                    tabindex="0"
                                    role="button"
                                    class="role-input input sm:text-base xl:text-lg 2xl:text-xl font-normal pl-1 h-7"
                                >
                                    {currentProfile.role === undefined
                                        ? ""
                                        : toTitleCase(currentProfile.role)}
                                </div>
                                <ul
                                    tabindex="0"
                                    class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-1 mt-1 shadow"
                                >
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".role-input").innerHTML =
                                                    "Brand";
                                            }}
                                        >
                                            Brand
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".role-input").innerHTML =
                                                    "Customer";
                                            }}
                                        >
                                            Customer
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".role-input").innerHTML =
                                                    "Admin";
                                            }}
                                        >
                                            Admin
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="name-title flex-none">Họ và tên:&nbsp;</div>
                            <input
                                type="text"
                                placeholder={
                                    currentProfile.fullName === undefined
                                        ? ""
                                        : toTitleCase(currentProfile.fullName)
                                }
                                class="name-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".name-title", ".name-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="email-title flex-none">Email:&nbsp;</div>
                            <input
                                type="text"
                                placeholder={currentProfile.email}
                                class="email-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".email-title", ".email-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="phone-title flex-none">Điện thoại:&nbsp;</div>
                            <input
                                type="text"
                                placeholder={currentProfile.phoneNumber}
                                class="phone-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap pl-1 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".phone-title", ".phone-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="gender-title flex-none">Giới tính:&nbsp;</div>
                            <div class="dropdown p-0 h-7 w-full">
                                <div
                                    tabindex="0"
                                    role="button"
                                    class="gender-input input sm:text-base xl:text-lg 2xl:text-xl font-normal pl-1 h-7"
                                >
                                    {currentProfile.gender === "male" ? "Nam" : "Nữ"}
                                </div>
                                <ul
                                    tabindex="0"
                                    class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-1 mt-1 shadow"
                                >
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".gender-input").innerHTML =
                                                    "Nam";
                                            }}
                                        >
                                            Nam
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".gender-input").innerHTML =
                                                    "Nữ";
                                            }}
                                        >
                                            Nữ
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="status-title flex-none">Trạng thái:&nbsp;</div>
                            <div class="dropdown p-0 h-7 w-full">
                                <div
                                    tabindex="0"
                                    role="button"
                                    class="status-input input sm:text-base xl:text-lg 2xl:text-xl font-normal pl-1 h-7"
                                >
                                    {currentProfile.status === "Active" ? "Hoạt động" : "Bị khóa"}
                                </div>
                                <ul
                                    tabindex="0"
                                    class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-1 mt-1 shadow"
                                >
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".status-input").innerHTML =
                                                    "Hoạt động";
                                            }}
                                        >
                                            Hoạt động
                                        </a>
                                    </li>
                                    <li>
                                        <a
                                            onClick={() => {
                                                document.querySelector(".status-input").innerHTML =
                                                    "Bị khóa";
                                            }}
                                        >
                                            Bị khóa
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="flex-none facebook-title">Facebook:&nbsp;</div>
                            <input
                                type="text"
                                placeholder={currentProfile.accountFacebook}
                                class="facebook-input input w-full sm:text-base xl:text-lg 2xl:text-xl p-0 h-7 placeholder-black"
                                onKeyUp={() => {
                                    inputFill(".facebook-title", ".facebook-input");
                                }}
                            />
                        </div>
                        <div class="flex sm:text-base xl:text-lg 2xl:text-xl">
                            <div class="birth-title flex-none">Ngày sinh:&nbsp;</div>
                            <input
                                type="text"
                                placeholder={currentProfile.dayOfBirth}
                                class="birth-input input sm:text-base xl:text-lg 2xl:text-xl whitespace-nowrap p-0 h-7 placeholder-black w-full"
                                onKeyUp={() => {
                                    inputFill(".birth-title", ".birth-input");
                                }}
                            />
                        </div>
                    </div>
                </div>
            </div>
            <div class="lg:hidden">
                <div class="flex flex-col w-full p-5">
                    <div class="flex flex-col items-center">
                        <div class="font-bold text-lg sm:text-xl text-info mb-5">
                            DANH SÁCH NGƯỜI DÙNG
                        </div>
                        <div class="w-full h-[500px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100 mb-5">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 text-base">STT</th>
                                        <th class="font-bold text-red-500 text-base">
                                            Tên tài khoản
                                        </th>
                                        <th class="font-bold text-red-500 text-base">Vai trò</th>
                                        <th class="font-bold text-red-500 text-base text-center">
                                            Chỉnh sửa
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {profileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    showUserProfile(index, true);
                                                }}
                                            >
                                                <td class="text-sm">{index + 1}</td>
                                                <td class="text-sm">{obj.userName}</td>
                                                <td class="text-sm">{toTitleCase(obj.role)}</td>
                                                <td class="text-center">
                                                    <button
                                                        class="btn btn-xs btn-square btn-info brightness-200 m-1"
                                                        onClick={(e) => {
                                                            e.stopPropagation();
                                                            editUserProfile(index);
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-4 w-4"
                                                            viewBox="0 0 24 24"
                                                            fill="none"
                                                            stroke="#000000"
                                                            stroke-width="2"
                                                            stroke-linecap="round"
                                                            stroke-linejoin="round"
                                                        >
                                                            <polygon points="14 2 18 6 7 17 3 17 3 13 14 2"></polygon>
                                                            <line
                                                                x1="3"
                                                                y1="22"
                                                                x2="21"
                                                                y2="22"
                                                            ></line>
                                                        </svg>
                                                    </button>
                                                    <button
                                                        class="btn btn-xs btn-square btn-error brightness-105 m-1"
                                                        onClick={(e) => {
                                                            e.stopPropagation();
                                                            removeUserProfile(index, true);
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-4 w-4"
                                                            fill="none"
                                                            viewBox="0 0 24 24"
                                                            stroke="currentColor"
                                                        >
                                                            <path
                                                                stroke-linecap="round"
                                                                stroke-linejoin="round"
                                                                stroke-width="2"
                                                                d="M6 18L18 6M6 6l12 12"
                                                            />
                                                        </svg>
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="flex flex-col grow items-center">
                        <div class="font-bold text-lg sm:text-xl text-info mb-5">
                            DANH SÁCH HỒ SƠ CHỜ
                        </div>
                        <div class="w-full h-[500px] overflow-x-hidden overflow-y-scroll no-scrollbar bg-base-100">
                            <table class="table">
                                <thead class="sticky top-0 bg-white z-10">
                                    <tr>
                                        <th class="font-bold text-red-500 text-base">STT</th>
                                        <th class="font-bold text-red-500 text-base">
                                            Tên tài khoản
                                        </th>
                                        <th class="font-bold text-red-500 text-base">Vai trò</th>
                                        <th class="font-bold text-red-500 text-base text-center">
                                            Xác nhận
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {newProfileData.map((obj, index) => {
                                        return (
                                            <tr
                                                class="hover cursor-pointer"
                                                onClick={() => {
                                                    showNewUserProfile(index);
                                                }}
                                            >
                                                <td class="text-sm">{index + 1}</td>
                                                <td class="text-sm">{obj.userName}</td>
                                                <td class="text-sm">{toTitleCase(obj.role)}</td>
                                                <td class="text-center">
                                                    <button class="btn btn-xs btn-square btn-success brightness-125 m-1">
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-4 w-4"
                                                            viewBox="0 0 24 24"
                                                            fill="none"
                                                            stroke="#000000"
                                                            stroke-width="2"
                                                            stroke-linecap="round"
                                                            stroke-linejoin="round"
                                                        >
                                                            <polyline points="20 6 9 17 4 12"></polyline>
                                                        </svg>
                                                    </button>
                                                    <button
                                                        class="btn btn-xs btn-square btn-error brightness-105 m-1"
                                                        onClick={(e) => {
                                                            e.stopPropagation();
                                                            removeNewUserProfile(index, false);
                                                        }}
                                                    >
                                                        <svg
                                                            xmlns="http://www.w3.org/2000/svg"
                                                            class="h-4 w-4"
                                                            fill="none"
                                                            viewBox="0 0 24 24"
                                                            stroke="currentColor"
                                                        >
                                                            <path
                                                                stroke-linecap="round"
                                                                stroke-linejoin="round"
                                                                stroke-width="2"
                                                                d="M6 18L18 6M6 6l12 12"
                                                            />
                                                        </svg>
                                                    </button>
                                                </td>
                                            </tr>
                                        );
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div
                    class="backdrop-blur-sm w-full h-[1000px] absolute top-0 z-20 cursor-pointer layer-blur layer-hidden"
                    onClick={hideUserProfile}
                ></div>
                <div class="card card-side bg-base-200 shadow-2xl max-w-[60%] absolute top-[35%] sm:top-[30%] md:top-[28%] left-[20%] z-20 user-profile layer-hidden">
                    <div class="card-body p-0">
                        <div class="flex w-full items-center">
                            <figure class="w-1/2">
                                <img class="object-cover" src={avatar} alt="Album" />
                            </figure>
                            <div class="flex flex-col items-center w-1/2">
                                <div class="font-bold text-lg sm:text-xl md:text-2xl text-red-500">
                                    {currentProfile.userName}
                                </div>
                                <div class="badge badge-info">
                                    {currentProfile.role === undefined
                                        ? ""
                                        : toTitleCase(currentProfile.role)}
                                </div>
                            </div>
                        </div>
                        <div class="p-4 flex flex-col sm:items-center">
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Họ và tên:&nbsp;</div>
                                <div>
                                    {currentProfile.fullName === undefined
                                        ? ""
                                        : toTitleCase(currentProfile.fullName)}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Email:&nbsp;</div>
                                <div>{currentProfile.email}</div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Điện thoại:&nbsp;</div>
                                <div>{currentProfile.phoneNumber}</div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Giới tính:&nbsp;</div>
                                <div>{currentProfile.gender === "male" ? "Nam" : "Nữ"}</div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Facebook:&nbsp;</div>
                                <div>{currentProfile.accountFacebook}</div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Ngày sinh:&nbsp;</div>
                                <div>{currentProfile.dayOfBirth}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card lg:card-side bg-base-200 shadow-2xl w-[60%] absolute top-[115%] sm:top-[110%] md:top-[108%] left-[20%] z-20 new-user-profile layer-hidden">
                    <div class="card-body p-0">
                        <div class="flex w-full items-center">
                            <figure class="w-1/2">
                                <img class="object-cover" src={avatar} alt="Album" />
                            </figure>
                            <div class="flex flex-col items-center w-1/2">
                                <div class="font-bold text-base sm:text-xl md:text-2xl text-red-500">
                                    {currentProfile.userName}
                                </div>
                                <div class="badge badge-info">
                                    {currentProfile.role === undefined
                                        ? ""
                                        : toTitleCase(currentProfile.role)}
                                </div>
                            </div>
                        </div>
                        <div class="p-4 flex flex-col sm:items-center">
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Họ và tên:&nbsp;</div>
                                <div>
                                    {currentProfile.fullName === undefined
                                        ? ""
                                        : toTitleCase(currentProfile.fullName)}
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Email:&nbsp;</div>
                                <div>{currentProfile.email}</div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Điện thoại:&nbsp;</div>
                                <div>{currentProfile.phoneNumber}</div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Giới tính:&nbsp;</div>
                                <div>{currentProfile.gender === "male" ? "Nam" : "Nữ"}</div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Facebook:&nbsp;</div>
                                <div>{currentProfile.accountFacebook}</div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div>Ngày sinh:&nbsp;</div>
                                <div>{currentProfile.dayOfBirth}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card bg-base-200 shadow-2xl w-[50%] absolute top-[38%] left-[27%] md:left-[25%] z-20 remove-profile layer-hidden">
                    <div class="card-body">
                        <div class="font-bold text-center text-xl md:text-2xl text-red-500">
                            Xóa người dùng ?
                        </div>
                        <p class="text-base md:text-lg mb-5">
                            Thao tác này sẽ vĩnh viễn xóa thông tin của người dùng.
                        </p>
                        <div class="card-actions justify-center">
                            <button
                                class="btn btn-error brightness-105 text-white"
                                onClick={() => {
                                    confirmRemove();
                                    hideUserProfile();
                                }}
                            >
                                Xóa ngay
                            </button>
                        </div>
                    </div>
                </div>
                <div class="card bg-base-200 shadow-2xl w-[50%] absolute top-[118%] left-[27%] md:left-[25%] z-20 remove-new-profile layer-hidden">
                    <div class="card-body">
                        <div class="font-bold text-center text-xl md:text-2xl text-red-500">
                            Xóa hồ sơ ?
                        </div>
                        <p class="text-base md:text-lg mb-5">
                            Thao tác này sẽ vĩnh viễn xóa hồ sơ của người dùng.
                        </p>
                        <div class="card-actions justify-center">
                            <button
                                class="btn btn-error brightness-105 text-white"
                                onClick={() => {
                                    confirmRemove();
                                    hideUserProfile();
                                }}
                            >
                                Xóa ngay
                            </button>
                        </div>
                    </div>
                </div>
                <div class="card card-side bg-base-200 shadow-2xl max-w-[60%] absolute top-[28%] sm:top-[23%] md:top-[18%] left-[20%] z-20 edit-profile layer-hidden">
                    <div class="card-body p-0">
                        <div class="flex w-full items-center justify-between">
                            <figure class="w-1/2">
                                <img
                                    class="object-cover small-ava-image cursor-pointer"
                                    alt="Album"
                                    src={avatar}
                                    onClick={() => {
                                        document.querySelector(".small-ava-input").click();
                                    }}
                                />
                                <input
                                    type="file"
                                    class="small-ava-input hidden"
                                    accept=".jpg,.jpeg,.png"
                                />
                            </figure>
                            <button
                                className="small-save-button btn btn-success btn-circle btn-disabled brightness-125 w-1/2 mx-1"
                                onClick={() => {
                                    confirmSave("small");
                                }}
                            >
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    class="h-6 w-6"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="#ffffff"
                                    stroke-width="2"
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                >
                                    <path d="M19 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11l5 5v11a2 2 0 0 1-2 2z"></path>
                                    <polyline points="17 21 17 13 7 13 7 21"></polyline>
                                    <polyline points="7 3 7 8 15 8"></polyline>
                                </svg>
                            </button>
                        </div>
                        <div class="p-4 flex flex-col">
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-username-title flex-none sm:text-lg md:text-xl">
                                    Tên tài khoản:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    placeholder={currentProfile.userName}
                                    class="small-username-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-username-title", ".small-username-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-role-title sm:text-lg md:text-xl flex-none">
                                    Vai trò:&nbsp;
                                </div>
                                <div class="dropdown p-0 h-6 w-full">
                                    <div
                                        tabindex="0"
                                        role="button"
                                        class="small-role-input input text-base sm:text-lg md:text-xl font-normal pl-1 h-6 mb-1"
                                    >
                                        {currentProfile.role === undefined
                                            ? ""
                                            : toTitleCase(currentProfile.role)}
                                    </div>
                                    <ul
                                        tabindex="0"
                                        class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-0 shadow"
                                    >
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-role-input"
                                                    ).innerHTML = "Brand";
                                                }}
                                            >
                                                Brand
                                            </a>
                                        </li>
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-role-input"
                                                    ).innerHTML = "Customer";
                                                }}
                                            >
                                                Customer
                                            </a>
                                        </li>
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-role-input"
                                                    ).innerHTML = "Admin";
                                                }}
                                            >
                                                Admin
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-name-title flex-none sm:text-lg md:text-xl">
                                    Họ và tên:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    placeholder={
                                        currentProfile.fullName === undefined
                                            ? ""
                                            : toTitleCase(currentProfile.fullName)
                                    }
                                    class="small-name-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-name-title", ".small-name-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-email-title flex-none sm:text-lg md:text-xl">
                                    Email:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    placeholder={currentProfile.email}
                                    class="small-email-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-email-title", ".small-email-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-phone-title flex-none sm:text-lg md:text-xl">
                                    Điện thoại:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    placeholder={currentProfile.phoneNumber}
                                    class="small-phone-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-phone-title", ".small-phone-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-gender-title sm:text-lg md:text-xl flex-none">
                                    Giới tính:&nbsp;
                                </div>
                                <div class="dropdown p-0 h-6 w-full">
                                    <div
                                        tabindex="0"
                                        role="button"
                                        class="small-gender-input input text-base sm:text-lg md:text-xl font-normal pl-1 h-6 mb-1"
                                    >
                                        {currentProfile.gender === "male" ? "Nam" : "Nữ"}
                                    </div>
                                    <ul
                                        tabindex="0"
                                        class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-0 shadow"
                                    >
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-gender-input"
                                                    ).innerHTML = "Nam";
                                                }}
                                            >
                                                Nam
                                            </a>
                                        </li>
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-gender-input"
                                                    ).innerHTML = "Nữ";
                                                }}
                                            >
                                                Nữ
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="small-status-title flex-none sm:text-lg md:text-xl">
                                    Trạng thái:&nbsp;
                                </div>
                                <div class="dropdown p-0 h-6 w-full">
                                    <div
                                        tabindex="0"
                                        role="button"
                                        class="small-status-input input text-base sm:text-lg md:text-xl font-normal pl-1 h-6 mb-1"
                                    >
                                        {currentProfile.status === "Active"
                                            ? "Hoạt động"
                                            : "Bị khóa"}
                                    </div>
                                    <ul
                                        tabindex="0"
                                        class="dropdown-content menu bg-base-100 rounded-box z-[1] w-full p-0 shadow"
                                    >
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-status-input"
                                                    ).innerHTML = "Hoạt động";
                                                }}
                                            >
                                                Hoạt động
                                            </a>
                                        </li>
                                        <li>
                                            <a
                                                onClick={() => {
                                                    document.querySelector(
                                                        ".small-status-input"
                                                    ).innerHTML = "Bị khóa";
                                                }}
                                            >
                                                Bị khóa
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl mb-1">
                                <div class="flex-none small-facebook-title sm:text-lg md:text-xl">
                                    Facebook:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    placeholder={currentProfile.accountFacebook}
                                    class="small-facebook-input input w-full sm:text-lg md:text-xl pl-1 h-6 placeholder-black"
                                    onKeyUp={() => {
                                        inputFill(".small-facebook-title", ".small-facebook-input");
                                    }}
                                />
                            </div>
                            <div class="flex text-base sm:text-lg md:text-xl">
                                <div class="small-birth-title flex-none sm:text-lg md:text-xl">
                                    Ngày sinh:&nbsp;
                                </div>
                                <input
                                    type="text"
                                    placeholder={currentProfile.dayOfBirth}
                                    class="small-birth-input input sm:text-lg md:text-xl whitespace-nowrap pl-1 h-6 placeholder-black w-full"
                                    onKeyUp={() => {
                                        inputFill(".small-birth-title", ".small-birth-input");
                                    }}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default UserManage;
