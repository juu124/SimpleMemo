package kr.co.witches.simple.memo.presentations.base

interface OnPermissionChecked {

    /**
     * 권한 허용
     * @param permission : 권한 이름
     */
    fun onGranted(permission: String)

    /**
     * 권한 거부
     * @param permission : 권한 이름
     */
    fun onDenied(permission: String)

}