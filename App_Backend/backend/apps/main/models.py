from collections import Counter
from django.db import models
from django.utils import timezone
from apps.users.models import CustomUser


class Emotions(models.TextChoices):
    HAPPY = 'happy', "Счастье"
    SAD = 'sad', "Грусть"
    SCARED = 'scared', "Страх"
    DISAPPOINTED = 'disappointed', "Разочарование"
    ANGRY = 'angry', "Злость"


class DayStats(models.Model):
    """Information for whole day"""

    class Meta:
        verbose_name = "статистика за день"
        verbose_name_plural = "статистика за день"
        constraints = [models.UniqueConstraint(fields=['user', 'date'], name='user_date_unique')]

    user = models.ForeignKey(CustomUser, models.CASCADE, related_name='days', verbose_name="пользователь")
    date = models.DateField(default=timezone.now, verbose_name="дата")
    text = models.TextField(blank=True, verbose_name="текст")

    @property
    def overall_emotion(self) -> str | None:
        """Return most common emotion of the day"""
        all_emotions = self.notes.values_list('emotion', flat=True)
        if not all_emotions:
            return None
        return Counter(all_emotions).most_common(1)[0][0]

    def __str__(self):
        return f"{self.date} {self.user.username}"


class Note(models.Model):
    """Information at specific moment"""

    class Meta:
        verbose_name = "запись"
        verbose_name_plural = "записи"

    day = models.ForeignKey(DayStats, models.CASCADE, related_name='notes', verbose_name="статистика за день")
    time = models.TimeField(auto_now_add=True, verbose_name="время")
    text = models.TextField(blank=True, verbose_name="текст")
    emotion = models.CharField(max_length=20, choices=Emotions.choices, verbose_name="эмоция")

    def __str__(self):
        return f"{self.day.date} {self.time} {self.day.user.username}"
