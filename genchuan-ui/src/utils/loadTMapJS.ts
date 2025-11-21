export const loadTMapJS = () => {
  return new Promise<void>((resolve, reject) => {
    if ((window as any).qq && (window as any).qq.maps) {
      resolve((window as any).qq.maps)
      return
    }
    const script = document.createElement('script')
    script.type = 'text/javascript'
    script.src = `https://map.qq.com/api/gljs?libraries=tools&v=1.exp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77`
    script.onload = () => resolve()
    script.onerror = () => reject(new Error('腾讯地图SDK加载失败'))
    document.head.appendChild(script)
  })
}
