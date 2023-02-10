USE master 
GO 
  --    DROP DATABASE QuanLyThuVien
go
CREATE DATABASE QuanLyThuVien
go
use master
USE QuanLyThuVien
GO
CREATE TABLE [DBO].[DocGia](
[MaDG] [NVARCHAR](50) NOT NULL PRIMARY KEY,
[PassWord] [NVARCHAR](50),
[Ten] [NVARCHAR](50),
[NgaySinh] [DATE],
[GioiTinh] [NVARCHAR](10),
[Email] [NVARCHAR](50),
[SoLuongMuon] [INT],
[TrangThaiTK] [NVARCHAR](50)
)
GO
CREATE TABLE [DBO].[QuanLy](
[MaQL] [NVARCHAR](50) NOT NULL PRIMARY KEY,
[PassWordQL] [NVARCHAR](50),
[Ten] [NVARCHAR](50),
[NgaySinh] [DATE],
[GioiTinh] [NVARCHAR](10),
[Email] [NVARCHAR](50) 
)
GO
CREATE TABLE [DBO].[TheLoai](
[MaTheLoai] [NVARCHAR](10) NOT NULL PRIMARY KEY,
[TenTheLoai] [NVARCHAR](100) NULL)
GO
CREATE TABLE [DBO].[DanhMucSach](
[MaDanhMucSach] [NVARCHAR](50) NOT NULL PRIMARY KEY,
[TenDMSach] [NVARCHAR](50)
)
go
CREATE TABLE [DBO].[PhieuMuon](
[MaPM] [NVARCHAR](50) NOT NULL PRIMARY KEY,
[NgayMuon] [DATE],
[MaDG] [NVARCHAR](50),
[MaQL] [NVARCHAR](50),
[GhiChu] [NVARCHAR](50),
[TrangThai] [NVARCHAR](50),
FOREIGN KEY (MaDG) REFERENCES DocGia(MaDG),
FOREIGN KEY (MaQL) REFERENCES QuanLy(MaQL)
)
GO
CREATE TABLE [DBO].[NhaXuatBan](
[MaNhaXB] [NVARCHAR](50) NOT NULL PRIMARY KEY,
[TenNhaXuatBan] [NVARCHAR](50)
)
GO
CREATE TABLE [DBO].[Sach](
[MaSach] [NVARCHAR](50) NOT NULL PRIMARY KEY,
[TenSach] [NVARCHAR](50),
[MaDanhMucSach] [NVARCHAR](50),
[MaTheLoai] [NVARCHAR](10),
[TacGia] [NVARCHAR](100), --50 -> 100 
[MaNhaXB] [NVARCHAR](50),
[NamXB] [INT],
[SoLuong] [INT],
[NoiDung] [NTEXT],
FOREIGN KEY (MaDanhMucSach) REFERENCES DanhMucSach(MaDanhMucSach),
FOREIGN KEY (MaTheLoai) REFERENCES TheLoai(MaTheLoai),
FOREIGN KEY (MaNhaXB) REFERENCES NhaXuatBan(MaNhaXB)

)
GO
CREATE TABLE [DBO].[ChiTietPhieuMuon](
[MaPM] [NVARCHAR](50) NOT NULL,
[MaSach] [NVARCHAR](50),
[NgayTra] [DATE],
[TienPhat] [INT],
[TinhTrangSach] [NVARCHAR](50),
FOREIGN KEY (MaSach) REFERENCES Sach(MaSach),
FOREIGN KEY (MaPM) REFERENCES PhieuMuon(MaPM)
)
GO
SET DATEFORMAT DMY
GO
--TRIGGER tăng số lượng mượn khi mượn 1 sách 
CREATE TRIGGER GiamSoLuongMuonKhiXoa_ThuCong on PhieuMuon 
FOR INSERT
AS
	BEGIN
		UPDATE DocGia
		SET	soluongMuon = soluongMuon + 1
		FROM DocGia, PhieuMuon, inserted
		WHERE inserted.MaPM = PhieuMuon.MaPM and DocGia.MaDG = PhieuMuon.MaDG
	END
GO
--Tăng số lượng mượn khi xóa sách tong 
CREATE TRIGGER TangSoLuongMuonKhiXoa_ThuCong on PhieuMuon 
FOR DELETE 
AS
	BEGIN
		UPDATE DocGia
		set	soluongMuon = soluongMuon - 1
		from DocGia, PhieuMuon, inserted
		where inserted.MaPM = PhieuMuon.MaPM and DocGia.MaDG = PhieuMuon.MaDG
	END
go
--TRIGGER giảm số lượng mượn khi trả sách <UPDATE> 
CREATE TRIGGER GiamSoLuongMuonKhiTraSach
ON ChiTietPhieuMuon FOR UPDATE
AS
	BEGIN 
		DECLARE @NgayTra DATE = (SELECT NgayTra FROM inserted)
		if (@NgayTra is not null)
			UPDATE DocGia
			SET	soluongMuon = soluongMuon - 1
			FROM DocGia, PhieuMuon, inserted
			WHERE inserted.MaPM = PhieuMuon.MaPM and DocGia.MaDG = PhieuMuon.MaDG
		ELSE 
			RETURN
	END
GO
-- giam so lượng sách khi mượn
CREATE TRIGGER Giamsoluongsachkhimuon
ON ChiTietPhieuMuon FOR insert
AS
	BEGIN 
		DECLARE @masach NVARCHAR(50) = (SELECT MaSach FROM inserted)
		if (@masach is not null)
			UPDATE Sach
			SET	SoLuong = SoLuong - 1
			FROM Sach, ChiTietPhieuMuon, inserted
			WHERE inserted.MaSach = ChiTietPhieuMuon.MaSach and Sach.MaSach = ChiTietPhieuMuon.MaSach
		ELSE 
			RETURN
	END
GO
--dang tes
-- Tăng số lượng sách khi xóa sách trong ctpm
/*CREATE TRIGGER TangsoLuongSach
ON ChiTietPhieuMuon FOR Delete
AS
	BEGIN 
		DECLARE @masach NVARCHAR(50) = (SELECT MaSach FROM inserted)
		if (@masach is not null)
			UPDATE Sach
			SET	SoLuong = SoLuong - 1
			FROM Sach, ChiTietPhieuMuon, inserted
			WHERE inserted.MaSach = ChiTietPhieuMuon.MaSach and Sach.MaSach = ChiTietPhieuMuon.MaSach
		ELSE 
			RETURN
	END
GO
*/




------insert-----
INSERT INTO [DBO].[DocGia] VALUES
('201101003','ad762fd6ebccc36e6e70208e5a3c193f',N'Tôn Hoàng Phúc','07-01-2002',N'Nam','tonhoangphuc92@gmail.com','0',N'Bình thường'),
('12345','827ccb0eea8a706c4c34a16891f84e7b',N'Tôn Hoàng Phúc','07-01-2002',N'Nam','tonhoangphuc92@gmail.com','0',N'Bình thường'),
('201101026','8f979aa43272dd32599c1bf4c3b8faa5',N'Nguyễn Duy Anh','04-02-2002',N'Nam',null,'0',N'Bình thường'),
('201101052','6876f99c8b744f8b1514bd553d7c9d1c',N'Nguyễn Đình Lộc','18-03-2000',N'Nam',null,'0',N'Bình thường'),
('201101002','a9ae219f9a0e7c3ff8a5c601a99ee930',N'Châu Thái Bảo','15-11-2002',N'Nam',null,'0',N'Bình thường'),
('sinhvien','615ad206666f8086103305b8f77173f4',N'Nguyễn Văn Sinh Viên','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('2011010793','8161171f3c593a097f25e7410f5d7574',N'Nguyễn Văn A','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('2011010794','386da9791a9d420ae1013c1558069666',N'Nguyễn Văn B','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('2011010795','a294ffb26ed6b3ef0224be131b849823',N'Nguyễn Văn C','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('2011010796','a1232dcca4891fbc1597756c61e6b7e7',N'Nguyễn Văn D','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('2011010797','1391dd5de0da0b7dbb36bac62f16d976',N'Nguyễn Văn E','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('2011010798','3e3888c0519e97b333dafc47e86ee2cf',N'Nguyễn Văn F','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('2011010799','814cbf9ec0807c5269bc8534cbcf1678',N'Nguyễn Văn G','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('2011010800','96b92e768ecb7800227d55436f3d3aee',N'Nguyễn Văn H','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('GV001','aee22ae84e37d01f0b68b11cacdcc6b6',N'HOÀNG NGỌC LONG','1-1-2002',N'Nam',null,'0',N'Bình thường'),
('GV002','46a41e9d578f742d76584d72c17fba50',N'LÊ VĂN HẠNH','1-1-1960',N'Nam',null,'0',N'Bình thường'),
('GV003','910c1852975a856b3ecab2cb95d9c0ce',N'NGUYỄN MINH ĐẾ','1-1-1980',N'Nam',null,'0',N'Bình thường'),
('GV004','b3c86a84ca1f4c07a2637b7c5b0542b7',N'ĐỖ VĂN NHƠN','1-1-1987',N'Nam',null,'0',N'Bình thường'),
('GV005','411d39645b4be9b656352becaf7879b5',N'MAI TRUNG THÀNH','1-1-1990',N'Nam',null,'0',N'Bình thường')




GO
INSERT INTO [DBO].[QuanLy] VALUES('admin','admin',N'Nguyễn Duy Anh','04-02-2002',N'Nam',null),
						('admin1','admin',N'Tôn Hoàng Phúc','07-01-2002',N'Nam',null),
						('admin2','admin',N'Nguyễn Duy Anh','04-02-2002',N'Nam',null)
GO
INSERT INTO [DBO].[DanhMucSach] VALUES(N'DM-NĐT', N'Chuyên ngành Điện-Điện tử'),
										(N'DM-NCK', N'Chuyên ngành Cơ khí'),
										(N'DM-IT', N'Chuyên ngành Công nghệ thông tin'),
										(N'DM-NLS', N'Chuyên ngành Lịch sử'),
										(N'DM-NXD', N'Chuyên ngành Xây dựng')
GO
INSERT INTO [DBO].[TheLoai] VALUES (N'SGT', N'Giáo trình học'),
(N'STK', N'Sách tham khảo'),
(N'SVH', N'Sách Văn hóa lịch sử'),
(N'SPL', N'Sách Chính trị, Pháp luật'),
(N'STC', N'Sách Tạp chí')
GO
INSERT INTO [DBO].[NhaXuatBan] VALUES (N'KT-TPHCM',N'NXB Kỹ thuật - Thành Phố Hồ Chí Minh'),
(N'Apress',N'NXB Apress'),
(N'Aspfree',N'NXB Aspfree'),
(N'Prentice Hall',N'NXB Prentice Hall'),
(N'KĐ',N'NXB Kim Đồng'),
(N'TN', N'NXB Thanh Niên'),
(N'LĐ',N'NXB Lao Động')
GO
INSERT INTO [DBO].[Sach] VALUES(N'978-604-922-549-9', N'Giáo Trình Luật Kinh Doanh', N'DM-NĐT', N'SGT',N'Võ Phước Long - Nguyễn Triều Hoa - Dương Kim Thể Nguyên', N'KT-TPHCM' , '2020','21', N'Độc giả đang cầm trên tay giáo trình Luật kinh doanh với tư cách là tư liệu khởi đầu trang bị cho người đọc những hiểu biết căn bản,...'),
(N'978-1-4842-6605-2', N'Beginning T-SQL', N'DM-NCK', N'STK', N'Kathi Kellenberger - Lee Everest', N'Apress', '2020', '7',N'You will: Install a sandboxed SQL Server instance for learning,....'),
(N'978-1-890774-95-0', N'ASP.NET 4.6 web programming with C# 2015',N'DM-NCK',N'STK',N'Mary Delamster - Anne Boehm',N'Aspfree','2015','19',N'Build multi-page Web Froms applications that get database data, manage session state, and use CSS and Boostrap for formating....'),
(N'978-0-13-417730-4', N'Core JAVA', N'DM-NCK',N'STK',N'Cay S.Horstmann',N'Prentice Hall', '2012','4',N'In late 1995, the Java programming language burst onto the Internet scene and gained instant celebrity status. The promise of java technology was...'),
(N'978-604-2-22725-4', N'AI -  Trí Tuệ Nhân Tạo',N'DM-NCK',N'STK',N'Lasse Rouhiainen',N'KĐ','2016','15',N'Hồi mười bảy tuổi, tôi biết tin một chiếc vi tính đã chiến thắng nhà vô dịch cờ vua thế giới Garry Kasparov. Chuyện này xả ra vào năm 1996 và chiếc máy tính...'),
(N'978-604-358-851-4', N'HELLO Các Bạn -  Mình Là Tôi Đi Code Code Dạo',N'DM-NCK',N'STK',N'Phạm Huy Hoàng', N'TN','2019','14',N'Cuốn sách chia sẻ vè việc lựa chọn ngành phát triển phần mềm dựa trên các yếu tố "sự kiên trì và nhẫn nại (tiến từng bước nhỏ), improve bản thân dựa trên các thiếu sót và sự mạo hiểm" cùng với yếu tố "khả năng tư duy"...'),
(N'978-604-360-334-7', N'Nghệ Thuật Quản Lý Tài Chính Cá Nhân',N'DM-IT',N'SVH',N'Brian Tracy - Dan Strutzel',N'LĐ','2014','23',N'TIỀN BẠC -  niềm đam mê bất tận và nổi khổ đau cùng cực, chủ đề cực độ nhạy cảm nhưng tột cùng cuốn hút của nhân loại. Ai cũng muốn kiếm tiền để được tự do tài chính, nhưng bạn thcuwj sự dành bao nhiêu tâm suucws cho nó?...'),
(N'978-604-59-7908-2', N'8 Bí Quyết Làm Việc Hiệu Quả',N'DM-NXD',N'SPL',N'Marc Effron',N'LĐ','2018','11',N'Marc Effron, tác giả cuốn Leading the Way(tạm dịch: Dẫn đường) và One Page Talent Managerment (tạm dịch: Thuật quản trị nhân tài cô đọng), đã mang đến cho chúng ta một tấm bản đồ đặc biets giúp khai thác tối đa tiềm năng và đạt được hiệu quả làm việc cao...'),

(N'978-604-2-27953-6', N'Sẻ Nâu Mơ Thành Đại Bàng',N'DM-NLS',N'SVH',N'Thủy Nguyên',N'KĐ','2009','8',N'Bé cùng ba mẹ mở quyển sách này ra, lắng nghe từng câu chuyện dễ thương về các bạn chim: Bạn Sẻ Nâu nhút nhát nhưng muốn thành Đại Bàng, bạn Chích Chòe ham ăn nên trồng cả vườn nho, bạn Chim Sâu chăm chỉ với món quà sinh nhật vô cùng đặc biệt...'),
(N'978-604-2-27921-5', N'Nơi Ta Thuộc Về',N'DM-NLS',N'SVH',N'Chúp Chúp',N'KĐ','2012','6',N' Nơi ta thuộc về được viết dưới góc nhìn của Suối Nhỏ, cậu bé loài người sống trong một thế giới nơi mà con người chịu sự phân biệt và định kiến vô cùng nặng nề từ muôn loài. Như một hành trình dài để trưởng thành, Suối Nhỏ đã cố gắng vượt qua định kiến xã hội, những nỗi sợ bên trong, để có thể trở nên dũng cảm, đối diện với những nghi ngại về bản thân, để thực sự tìm ra được mình là ai, mình muốn và cần làm gì trong cuộc đời...'),
(N'978-604-2-28070-9', N'Người Lính Phi Công Kể Chuyện',N'DM-NLS',N'SVH',N'Nguyễn Công Huy',N'KĐ','2005','13',N'Góp phần trong chiến thắng vang dội này, có sự đóng góp không nhỏ của Lực lượng Không quân Tiêm kích của Quân chủng Phòng không – Không quân. Cựu phi công máy bay tiêm kích Nguyễn Công Huy là một nhân chứng sống, đã trực tiếp tham gia chiến đấu trên bầu trời Thủ đô vào những ngày tháng ác liệt nhất ấy. Tác giả đã kể lại hành trình của một người lính phi công, từ khi chập chững bước chân vào đời lính, đến những ngày đầu tiên bỡ ngỡ với bầu trời, cho đến những trận không chiến nảy lửa năm 1972...')



select * from Sach